package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.repository.UserRepository;
import com.udea.biosegura.persistence.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDTO userdto;

    private Optional<UserDTO> optUserDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userdto = new UserDTO();
        userdto.setIdUser("1035439685");
        userdto.setName("Felipe Triana");
        userdto.setPhone("3193662132");
        userdto.setEmail("felipetrianago@gmail.com");
        userdto.setAddress("Calle 123");

        optUserDto = Optional.of(userdto);   //Wrap for userdto, used in getUser
        when(userRepository.getUser("1035439685")).thenReturn(optUserDto); //Needed for delete() and getUser()
    }

    @Test
    void getAll() {
        when(userRepository.getAll()).thenReturn(Arrays.asList(userdto));
        assertNotNull(userService.getAll());
    }

    @Test
    void getUser() {

        Optional<UserDTO> reponseService;
        reponseService = userService.getUser("1035439685");
        assertEquals(Optional.of("3193662132"), reponseService.map(usr -> usr.getPhone()));
    }

    @Test
    void save() {
        when(userRepository.save(any(UserDTO.class))).thenReturn(userdto);
        assertNotNull(userService.save(new UserDTO()));
    }

    @Test
    void delete() {
        when(userRepository.delete("1035439685")).thenReturn("Success");
        boolean responseService = userService.delete("1035439685");
        System.out.println(responseService);
        assertEquals(true, responseService);
    }

}