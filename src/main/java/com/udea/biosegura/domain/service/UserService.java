package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll(){return userRepository.getAll();}


    public UserDTO save(UserDTO userdto){return userRepository.save(userdto);}

    /*public boolean delete(String idUser){
        return getUser(idUser).map(clt -> {
            userRepository.delete(idUser);
            return true;
        }).orElse(false);
    }*/

}
