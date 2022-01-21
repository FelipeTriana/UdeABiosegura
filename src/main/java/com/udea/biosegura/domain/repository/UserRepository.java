package com.udea.biosegura.domain.repository;

import com.udea.biosegura.domain.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<UserDTO> getAll();
    Optional<UserDTO> getUser(String idUser);
    UserDTO save(UserDTO userdto);
    void delete(String idUser);

    
}
