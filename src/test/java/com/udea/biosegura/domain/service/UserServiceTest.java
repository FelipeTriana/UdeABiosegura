package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.repository.UserRepository;
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

    }

    @Test
    void getAll() {
        when(userRepository.getAll()).thenReturn(Arrays.asList(userdto));
        assertNotNull(userService.getAll());
    }

    @Test
    void getUser() {
        when(userRepository.getUser("1035439685")).thenReturn(optUserDto);
        assertNotNull(userService.getUser("1035439685"));
    }

    @Test
    void save() {
        when(userRepository.save(any(UserDTO.class))).thenReturn(userdto);
        assertNotNull(userService.save(new UserDTO()));
    }

    @Test
    void delete() {
        when(userRepository.delete("1035439685")).thenReturn(true);
        assertNotNull(userService.delete("1035439685"));
    }

}