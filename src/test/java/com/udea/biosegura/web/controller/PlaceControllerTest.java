package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.service.PlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PlaceControllerTest {

    @Mock
    private PlaceService placeService;

    @InjectMocks
    private PlaceController placeController;
    private PlaceDTO placedto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        placedto = new PlaceDTO();
        placedto.setIdPlace("1");
        placedto.setNamePlace("Auditorio");
        placedto.setPhone("2756325");
        placedto.setAddress("Bloque 19");
        placedto.setCapacity(50);
    }

    @Test
    public void shouldReturnAListOfPlaces(){
        //Arrange
        when(placeService.getAll()).thenReturn(Arrays.asList(placedto));

        //Act
        List<PlaceDTO> list = placeController.getAll().getBody();

        //Assert
        assertNotNull(list);
    }
    @Test
    public void shouldReturnStatusCode200WhenTheListOfPlacesIsRequested(){
        //Arrange
        when(placeService.getAll()).thenReturn(Arrays.asList(placedto));

        //Act
        HttpStatus code = placeController.getAll().getStatusCode();

        //Assert
        assertEquals(HttpStatus.OK.value(), code.value());
    }
}