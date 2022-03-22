package com.udea.biosegura.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InvitationDTOTest {

    @DisplayName("Should create an InvitationDTO Object")
    @Test
    public void shouldNotReturnNull(){

        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser("1");
        userDTO.setName("User Name");
        userDTO.setEmail("username@mail.com");
        userDTO.setAddress("username address");
        userDTO.setPhone("3579517845");

        PlaceDTO placeDTO = new PlaceDTO();
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

        InvitationDTO invitationDTO = new InvitationDTO();
        invitationDTO.setInvitationId(1);
        invitationDTO.setUserId("1");
        invitationDTO.setPlaceId("1");
        invitationDTO.setUser(userDTO);
        invitationDTO.setPlace(placeDTO);
        invitationDTO.setInDate(inDate);
        invitationDTO.setOutDate(outDate);

        assertNotNull(invitationDTO);
    }
}