package com.udea.biosegura.persistence.repository;

import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.persistence.crud.UserCrudRepository;
import com.udea.biosegura.persistence.entity.User;
import com.udea.biosegura.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.udea.biosegura.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserPersistenceRepository implements UserRepository {
    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserMapper mapper;

    @Override

    public List<UserDTO> getAll() {
        List<User> users = (List<User>) userCrudRepository.findAll(); //Ready
        return mapper.toUsersDTO(users);
    }

    @Override
    public Optional<UserDTO> getUser(String idUser) {
        return Optional.empty();
    }

    @Override
    public UserDTO save(UserDTO userdto) {
        User user = mapper.toUser(userdto);
        return mapper.toUserDTO(userCrudRepository.save(user));      //Ready
    }

    @Override
    public void delete(String idUser) {
        userCrudRepository.deleteById(idUser);                       //Ready
    }
}
