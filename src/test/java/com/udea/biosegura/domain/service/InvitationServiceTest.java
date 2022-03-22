package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.repository.InvitationRepository;
import com.udea.biosegura.domain.repository.PlaceRepository;
import com.udea.biosegura.domain.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class InvitationServiceTest {

    @Mock
    private InvitationRepository invitationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
    private InvitationService invitationService;

    private InvitationDTO invitationDTO;

    private Optional<InvitationDTO> optInvitationDTO;

    private Optional<UserDTO> optUserDTO;

    private Optional<PlaceDTO> optPlaceDTO;

    private UserDTO userDTO;

    private PlaceDTO placeDTO;
    @BeforeEach()
    void setUp(){
        MockitoAnnotations.initMocks(this);
        invitationDTO = new InvitationDTO();

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
        placeDTO.setCheckIn("6:00");
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

        optUserDTO = Optional.of(userDTO);
        optPlaceDTO = Optional.of(placeDTO);
        optInvitationDTO = Optional.of(invitationDTO);
    }

    @DisplayName("Should return a list of InvitationDTO Objects")
    @Test
    void getAll(){
        when(invitationRepository.getAll()).thenReturn(Arrays.asList(invitationDTO));
        assertNotNull(invitationService.getAll());
        assertEquals(1, invitationService.getAll().size());
    }

    @DisplayName("Should return a found Invitation")
    @Test
    void getInvitation(){
        when(invitationRepository.getInvitation(1)).thenReturn(optInvitationDTO);

        Optional<InvitationDTO> response = invitationService.getInvitation(1);
        assertNotNull(response);
        assertEquals(1, response.get().getInvitationId());
    }

    @DisplayName("Should create an Invitation")
    @Test
    void save(){
        when(userRepository.getUser("1")).thenReturn(Optional.of(userDTO));
        when(placeRepository.getPlace("1")).thenReturn(Optional.of(placeDTO));
        when(invitationRepository.save(any(InvitationDTO.class))).thenReturn(invitationDTO);
        assertNotNull(invitationService.save(invitationDTO));
    }

    @DisplayName("Should delete an invitation by its id")
    @Test
    void deleteByInvitationId(){
        when(userRepository.getUser("1")).thenReturn(Optional.of(userDTO));
        when(placeRepository.getPlace("1")).thenReturn(Optional.of(placeDTO));
        when(invitationRepository.getInvitation(1)).thenReturn(optInvitationDTO);
        when(invitationRepository.delete(1)).thenReturn("Success");
        boolean response = invitationService.delete(1);
        assertTrue(response);
    }

    @DisplayName("Should delete an invitation by its place id")
    @Test
    void deleteByPlace(){
        when(userRepository.getUser("1")).thenReturn(Optional.of(userDTO));
        when(placeRepository.getPlace("1")).thenReturn(Optional.of(placeDTO));
        when(invitationRepository.getInvitation(1)).thenReturn(optInvitationDTO);
        when(invitationRepository.getAll()).thenReturn(Arrays.asList(invitationDTO));
        when(invitationRepository.delete(1)).thenReturn("Success");

        boolean response = invitationService.deleteByPlace("1");

        assertTrue(response);
    }



}