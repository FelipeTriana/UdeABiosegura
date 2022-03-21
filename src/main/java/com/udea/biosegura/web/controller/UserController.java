package com.udea.biosegura.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.service.TokenService;
import com.udea.biosegura.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("idUser") String id) {
        return userService.getUser(id)
                .map(usr-> new ResponseEntity<>(usr, HttpStatus.OK))
                .orElse(new ResponseEntity("404 not found",HttpStatus.NOT_FOUND)); //Se quito <>
    }

    @PostMapping()
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userdto) {
        return new ResponseEntity<>(userService.save(userdto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<UserDTO> delete(@PathVariable("idUser") String id) {

        if (userService.delete(id)) {
            return new ResponseEntity("Delete Successfuly",HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/login")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        String value = headers.getFirst(HttpHeaders.AUTHORIZATION);
        String email = tokenService.obtainUserFromToken(value);
        return  userService.getUserWithEmail(email)
                .map(user-> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity("404 not found",HttpStatus.NOT_FOUND));
    }



}
