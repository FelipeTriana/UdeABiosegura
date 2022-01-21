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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    private UserDTO userdto;

    private List<InvitationDTO> invitations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userdto = new UserDTO();
        userdto.setIdUser("1035439685");
        userdto.setName("Felipe Triana");
        userdto.setPhone("3193662132");
        userdto.setEmail("felipetrianago@gmail.com");
        userdto.setAddress("Calle 123");
        //userdto.setInvitations(invitations);

    }

    @Test
    void getAll() {
        when(userRepository.getAll()).thenReturn(Arrays.asList(userdto));
        assertNotNull(userService.getAll());
    }

    @Test
    void save() {
        when(userRepository.save(any(UserDTO.class))).thenReturn(userdto);
        assertNotNull(userService.save(new UserDTO()));
    }
}