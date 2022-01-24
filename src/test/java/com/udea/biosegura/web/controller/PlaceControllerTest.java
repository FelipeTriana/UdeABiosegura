package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.service.PlaceService;
import com.udea.biosegura.persistence.entity.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void shouldReturnAPlaceWhenItsFoundByItsId(){
        //Arrange
        Optional<PlaceDTO> placeDTOOptional = Optional.of(placedto);
        when(placeService.getPlace(placedto.getIdPlace())).thenReturn(placeDTOOptional);
        String idToSearch = "1";
        String placeNameToCompare ="Auditorio";

        //Act
        PlaceDTO place = placeController.getPlace(idToSearch).getBody();

        //Assert
        assertEquals(placeNameToCompare, place.getNamePlace());
    }

    @Test
    public void shouldReturnStatusCode200WhenAPlaceItsFoundByItsId(){
        //Arrange
        Optional<PlaceDTO> placeDTOOptional = Optional.of(placedto);
        when(placeService.getPlace(placedto.getIdPlace())).thenReturn(placeDTOOptional);
        String idToSearch = "1";

        //Act
        HttpStatus code = placeController.getPlace(idToSearch).getStatusCode();

        //Assert
        assertEquals(HttpStatus.OK.value(), code.value());
    }

    @Test
    public void shouldReturnStatusCode404WhenAPlaceIsNotFoundByItsId(){
        //Arrange
        Optional<PlaceDTO> placeDTOOptional = Optional.of(placedto);
        when(placeService.getPlace(placedto.getIdPlace())).thenReturn(placeDTOOptional);
        String idToSearch = "2";

        //Act
        HttpStatus code = placeController.getPlace(idToSearch).getStatusCode();

        //Assert
        assertEquals(HttpStatus.NOT_FOUND.value(), code.value());

    }

    @Test
    public void shouldReturnTheCreatedPlace(){
        //Arrange
        when(placeService.save(any(PlaceDTO.class))).thenReturn(new PlaceDTO());

        //Act
        PlaceDTO place = placeController.save(new PlaceDTO()).getBody();

        //Assert
        assertNotNull(place);
    }

    @Test
    public void shouldReturnStatusCode201WhenAPlaceIsCreated(){
        //Arrange
        when(placeService.save(any(PlaceDTO.class))).thenReturn(new PlaceDTO());

        //Act
        HttpStatus code = placeController.save(new PlaceDTO()).getStatusCode();

        //Assert
        assertEquals(HttpStatus.CREATED.value(), code.value());
    }

}