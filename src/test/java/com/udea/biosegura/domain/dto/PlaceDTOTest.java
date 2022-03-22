package com.udea.biosegura.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceDTOTest {

    @DisplayName("Should create a PlaceDTO Object")
    @Test
    public void mustNotReturnNull(){
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

        assertNotNull(placeDTO);
    }
}