package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.repository.InvitationRepository;
import com.udea.biosegura.domain.service.InvitationService;
import com.udea.biosegura.domain.service.PlaceService;
import com.udea.biosegura.models.CreateInvitationInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class InvitationControllerTest {

    @Mock
    InvitationService invitationService;

    @Mock
    PlaceService placeService;

    @Mock
    InvitationRepository invitationRepository;

    @InjectMocks
    InvitationController invitationController;
    InvitationDTO invitationDTO;
    UserDTO userDTO;
    PlaceDTO placeDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

        userDTO = new UserDTO();
        userDTO.setIdUser("1");
        userDTO.setName("User Name");
        userDTO.setEmail("username@mail.com");
        userDTO.setAddress("username address");
        userDTO.setPhone("3579517845");

        placeDTO = new PlaceDTO();
        placeDTO.setIdPlace("1");
        placeDTO.setNamePlace("Place 1");
        placeDTO.setAddress("10-110");
        placeDTO.setPhone("604 4351722");
        placeDTO.setCapacity(50);
        placeDTO.setActualCapacity(50);
        placeDTO.setCheckIn("06:00");
        placeDTO.setCheckOut("10:00");
        placeDTO.setImgUrl("https://i.imgur.com/LmJSpwb.jpg");

        LocalDateTime inDate = LocalDateTime.of(2022, 3, 22, 7, 10);
        LocalDateTime outDate = LocalDateTime.of(2022, 3, 22, 9, 40);

        invitationDTO = new InvitationDTO();
        invitationDTO.setInvitationId(1);
        invitationDTO.setUserId("1");
        invitationDTO.setPlaceId("1");
        invitationDTO.setUser(userDTO);
        invitationDTO.setPlace(placeDTO);
        invitationDTO.setInDate(inDate);
        invitationDTO.setOutDate(outDate);
    }

    @DisplayName("Should return a list of invitations in the body")
    @Test
    void getAllBodyTest(){
        when(invitationService.getAll()).thenReturn(Arrays.asList(invitationDTO));

        List<InvitationDTO> body = invitationController.getAll().getBody();

        assertNotNull(body);
    }

    @DisplayName("Should return status code 200 when the list of invitations are returned")
    @Test
    void getAllStatusCodeTest(){
        when(invitationService.getAll()).thenReturn(Arrays.asList(invitationDTO));

        HttpStatus code = invitationController.getAll().getStatusCode();

        assertEquals(HttpStatus.OK.value(), code.value());
    }

    @DisplayName("Should return an invitation found by its id")
    @Test
    void getByIdBodyTest(){
        when(invitationService.getInvitation(1)).thenReturn(Optional.of(invitationDTO));

        InvitationDTO found = invitationController.getInvitation(1).getBody();

        assertNotNull(found);
    }

    @DisplayName("Should return status code 200 when an invitation is found by its id")
    @Test
    void getByIdStatusCodeTest(){
        when(invitationService.getInvitation(1)).thenReturn(Optional.of(invitationDTO));

        HttpStatus code = invitationController.getInvitation(1).getStatusCode();

        assertEquals(HttpStatus.OK.value(), code.value());
    }

    @DisplayName("Should return status code 200 when an invitation is created")
    @Test
    void saveInvitationStatusCodeTest(){
        when(placeService.getPlace("1")).thenReturn(Optional.of(placeDTO));
        when(invitationService.save(any(InvitationDTO.class))).thenReturn(new InvitationDTO());

        CreateInvitationInput invitationInput = new CreateInvitationInput();
        invitationInput.setInvitationId(invitationDTO.getInvitationId());
        invitationInput.setUserId(invitationDTO.getUserId());
        invitationInput.setPlaceId(invitationDTO.getPlaceId());
        invitationInput.setInDate("2022-03-22 07:00");
        invitationInput.setOutDate("2022-03-22 09:00");

        HttpStatus code = invitationController.save(invitationInput).getStatusCode();

        assertEquals(HttpStatus.CREATED.value(), code.value());
    }

    @DisplayName("Should return the body of the created invitation")
    @Test
    void saveInvitationBodyTest(){
        when(placeService.getPlace("1")).thenReturn(Optional.of(placeDTO));
        when(invitationService.save(any(InvitationDTO.class))).thenReturn(new InvitationDTO());

        CreateInvitationInput invitationInput = new CreateInvitationInput();
        invitationInput.setInvitationId(invitationDTO.getInvitationId());
        invitationInput.setUserId(invitationDTO.getUserId());
        invitationInput.setPlaceId(invitationDTO.getPlaceId());
        invitationInput.setInDate("2022-03-22 07:00");
        invitationInput.setOutDate("2022-03-22 09:00");

        InvitationDTO created = invitationController.save(invitationInput).getBody();

        assertNotNull(created);
    }

    /*
    @DisplayName("Should return a DateTimeParseException when the input dates are not in the needed format")
    @Test
    void saveInvitationParseExceptionTest(){
        when(placeService.getPlace("1")).thenReturn(Optional.of(placeDTO));
        when(invitationService.save(any(InvitationDTO.class))).thenReturn(new InvitationDTO());

        CreateInvitationInput invitationInput = new CreateInvitationInput();
        invitationInput.setInvitationId(invitationDTO.getInvitationId());
        invitationInput.setUserId(invitationDTO.getUserId());
        invitationInput.setPlaceId(invitationDTO.getPlaceId());
        invitationInput.setInDate("2022-03-22 07:00:00");
        invitationInput.setOutDate("2022-03-22 09:00");

        assertThrows(DateTimeParseException.class, () -> {
            invitationController.save(invitationInput).getBody();
        });
    }*/

    @DisplayName("Should return status code 400 when the out date is before the in date when creating an invitation")
    @Test
    void saveInvitationWithOutDateBeforeInDate(){
        when(placeService.getPlace("1")).thenReturn(Optional.of(placeDTO));
        when(invitationService.save(any(InvitationDTO.class))).thenReturn(new InvitationDTO());

        CreateInvitationInput invitationInput = new CreateInvitationInput();
        invitationInput.setInvitationId(invitationDTO.getInvitationId());
        invitationInput.setUserId(invitationDTO.getUserId());
        invitationInput.setPlaceId(invitationDTO.getPlaceId());
        invitationInput.setInDate("2022-03-22 09:00");
        invitationInput.setOutDate("2022-03-22 07:00");

        HttpStatus code = invitationController.save(invitationInput).getStatusCode();

        assertEquals(HttpStatus.BAD_REQUEST.value(), code.value());
    }

    @DisplayName("Should return status code 400 when the dates are not in the same day, month and year")
    @Test
    void saveInvitationWithDatesInDifferentDays(){
        when(placeService.getPlace("1")).thenReturn(Optional.of(placeDTO));
        when(invitationService.save(any(InvitationDTO.class))).thenReturn(new InvitationDTO());

        CreateInvitationInput invitationInput = new CreateInvitationInput();
        invitationInput.setInvitationId(invitationDTO.getInvitationId());
        invitationInput.setUserId(invitationDTO.getUserId());
        invitationInput.setPlaceId(invitationDTO.getPlaceId());
        invitationInput.setInDate("2022-04-22 07:00");
        invitationInput.setOutDate("2022-03-22 08:00");

        HttpStatus code = invitationController.save(invitationInput).getStatusCode();

        assertEquals(HttpStatus.BAD_REQUEST.value(), code.value());
    }

    @DisplayName("Should return status code 400 when the In and Out dates are out of the schedule for the place")
    @Test
    void saveInvitationWithDatesOutOfThePlaceSchedule(){
        PlaceDTO newPlaceDTO = new PlaceDTO();
        newPlaceDTO.setIdPlace("1");
        newPlaceDTO.setNamePlace("Place 1");
        newPlaceDTO.setAddress("10-110");
        newPlaceDTO.setPhone("604 4351722");
        newPlaceDTO.setCapacity(50);
        newPlaceDTO.setActualCapacity(50);
        newPlaceDTO.setCheckIn("10:00");
        newPlaceDTO.setCheckOut("11:00");
        newPlaceDTO.setImgUrl("https://i.imgur.com/LmJSpwb.jpg");

        when(placeService.getPlace("1")).thenReturn(Optional.of(newPlaceDTO));
        when(invitationService.save(any(InvitationDTO.class))).thenReturn(new InvitationDTO());

        CreateInvitationInput invitationInput = new CreateInvitationInput();
        invitationInput.setInvitationId(invitationDTO.getInvitationId());
        invitationInput.setUserId(invitationDTO.getUserId());
        invitationInput.setPlaceId(newPlaceDTO.getIdPlace());
        invitationInput.setInDate("2022-03-22 08:00");
        invitationInput.setOutDate("2022-03-22 09:00");

        HttpStatus code = invitationController.save(invitationInput).getStatusCode();

        assertEquals(HttpStatus.BAD_REQUEST.value(), code.value());
    }

    @DisplayName("Should return status code 200 when an invitation its deleted by its Id")
    @Test
    void deleteInvitationByIdSuccessfullyTest(){
        when(invitationService.delete(1)).thenReturn(true);

        HttpStatus code = invitationController.delete(1).getStatusCode();

        assertEquals(HttpStatus.OK.value(), code.value());
    }

    @DisplayName("Should return status code 404 when an invitation to delete is not found by its Id")
    @Test
    void deleteInvitationNotFoundTest(){
        when(invitationService.delete(1)).thenReturn(false);

        HttpStatus code = invitationController.delete(1).getStatusCode();

        assertEquals(HttpStatus.NOT_FOUND.value(), code.value());
    }

    @DisplayName("Should return status code 200 when an invitation its deleted by its place Id")
    @Test
    void deleteInvitationByPlaceIdSuccessfullyTest(){
        when(invitationService.deleteByPlace("1")).thenReturn(true);

        HttpStatus code = invitationController.deleteByPlace("1").getStatusCode();

        assertEquals(HttpStatus.OK.value(), code.value());
    }

    @DisplayName("Should return status code 404 when the place ID to delete an invitation is not found")
    @Test
    void deleteInvitationByIdNotFoundTest(){
        when(invitationService.deleteByPlace("1")).thenReturn(false);

        HttpStatus code = invitationController.deleteByPlace("1").getStatusCode();

        assertEquals(HttpStatus.NOT_FOUND.value(), code.value());
    }

}