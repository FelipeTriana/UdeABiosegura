package com.udea.biosegura.persistence.repository;

import com.udea.biosegura.domain.dto.UserDTO;
import org.springframework.stereotype.Repository;

import com.udea.biosegura.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserPersistenceRepository implements UserRepository {
    @Override
    public List<UserDTO> getAll() {
        return null;
    }

    @Override
    public Optional<UserDTO> getUser(String idUser) {
        return Optional.empty();
    }

    @Override
    public UserDTO save(UserDTO user) {
        return null;
    }

    @Override
    public void delete(String idUser) {

    }
}
