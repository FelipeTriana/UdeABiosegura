package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {


    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    private UserDTO userdto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userdto = new UserDTO();
        userdto.setIdUser("1035439685");
        userdto.setName("Mateo Baena");
        userdto.setPhone("3193662132");
        userdto.setEmail("mateo.baena@udea.edu.co");
        userdto.setAddress("Calle 123");

    }

    @Test
    public void shouldReturnAListOfUsersWhenTheListOfUsersIsRequested(){
        //Arrange
        when(userService.getAll()).thenReturn(Arrays.asList(userdto)); //Setting up the mock to return a list on the call to the service in the controller

        //Act
        List<UserDTO> list = userController.getAll().getBody(); //Assigning the body of the ResponseEntity

        assertNotNull(list); //Simply test if the body is not null

    }

    @Test
    public void shouldReturnTheStatusCode200WhenTheListOfUsersIsRequested(){
        //Arrange
        when(userService.getAll()).thenReturn(Arrays.asList(userdto));

        //Act
        HttpStatus code = userController.getAll().getStatusCode(); //Assigning the status code of the ResponseEntity

        assertEquals(HttpStatus.OK.value(), code.value()); //Simply test if the status code is the same
    }
    
}