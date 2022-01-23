package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.service.UserService;
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

        //Assert
        assertNotNull(list); //Simply test if the body is not null

    }

    //I think the test case for the empty List of Users is also required

    @Test
    public void shouldReturnTheStatusCode200WhenTheListOfUsersIsRequested(){
        //Arrange
        when(userService.getAll()).thenReturn(Arrays.asList(userdto));

        //Act
        HttpStatus code = userController.getAll().getStatusCode(); //Assigning the status code of the ResponseEntity

        //Assert
        assertEquals(HttpStatus.OK.value(), code.value()); //Simply test if the status code is the same
    }

    @Test
    public void shouldReturnAnUserWhenItsFoundByItsId(){

        /* getUser(id: string): Optional<UserDTO>
           -> Must wrap an UserDTO over an Optional<UserDTO>
           -> Must mock the getUser method to return the UserDTO established in the setUp()
        * */
        //Arrange
        Optional<UserDTO> userDTOOptional = Optional.of(userdto);
        when(userService.getUser(userdto.getIdUser())).thenReturn(userDTOOptional);

        String emailToCompare = "mateo.baena@udea.edu.co";
        String idToFound = "1035439685";

        //Act
        UserDTO foundUser = userController.getUser(idToFound).getBody();

        //Assert
        assertEquals(emailToCompare,foundUser.getEmail());
    }

    @Test
    public void shouldReturnStatusCode200WhenAnUserIsFoundByItsId(){
        //Arrange
        Optional<UserDTO> userDTOOptional = Optional.of(userdto);
        when(userService.getUser(userdto.getIdUser())).thenReturn(userDTOOptional);

        String idToFound = "1035439685";

        //Act
        HttpStatus code = userController.getUser(idToFound).getStatusCode();

        //Assert
        assertEquals(HttpStatus.OK.value(), code.value());
    }

    @Test
    public void shouldReturnStatusCode404WhenAnUserIsNotFoundByItsId(){
        //Arrange
        Optional<UserDTO> userDTOOptional = Optional.of(userdto);
        when(userService.getUser(userdto.getIdUser())).thenReturn(userDTOOptional);

        String idToFound = "1035439689";

        //Act
        HttpStatus code = userController.getUser(idToFound).getStatusCode();

        //Assert
        assertEquals(HttpStatus.NOT_FOUND.value(), code.value());
    }
}