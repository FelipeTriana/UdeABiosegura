package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
    private PlaceService placeService;

    private PlaceDTO placedto;

    private Optional<PlaceDTO> optPlaceDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        placedto = new PlaceDTO();
        placedto.setIdPlace("1");
        placedto.setNamePlace("Auditorio");
        placedto.setPhone("2756325");
        placedto.setAddress("Bloque 19");
        placedto.setCapacity(50);

        optPlaceDto = Optional.of(placedto);
        when(placeRepository.getPlace("1")).thenReturn(optPlaceDto);
    }

    @Test
    void getAll() {
        when(placeRepository.getAll()).thenReturn(Arrays.asList(placedto));
        assertNotNull(placeService.getAll());
    }

    @Test
    void getPlace() {

        Optional<PlaceDTO> reponseService;
        reponseService = placeService.getPlace("1");
        assertEquals(Optional.of("2756325"), reponseService.map(usr -> usr.getPhone()));
    }

    @Test
    void save() {
        when(placeRepository.save(any(PlaceDTO.class))).thenReturn(placedto);
        assertNotNull(placeService.save(new PlaceDTO()));
    }

    @Test
    void delete() {
        when(placeRepository.delete("1")).thenReturn("Success");
        boolean responseService = placeService.delete("1");
        System.out.println(responseService);
        assertEquals(true, responseService);
    }
}