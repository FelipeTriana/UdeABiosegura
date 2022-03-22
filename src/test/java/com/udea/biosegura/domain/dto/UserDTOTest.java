package com.udea.biosegura.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @DisplayName("Should create an UserDTO Object")
    @Test
    public void mustNotReturnNull(){
        UserDTO userDTO = new UserDTO();

        userDTO.setIdUser("1");
        userDTO.setName("User Name");
        userDTO.setEmail("username@mail.com");
        userDTO.setAddress("username address");
        userDTO.setPhone("3579517845");

        assertNotNull(userDTO);
    }
}