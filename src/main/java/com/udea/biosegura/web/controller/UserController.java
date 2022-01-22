package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("idUser") String id) {
        return userService.getUser(id)
                .map(usr-> new ResponseEntity<>(usr, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userdto) {
        return new ResponseEntity<>(userService.save(userdto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<UserDTO> delete(@PathVariable("idUser") String id) {
        if (userService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}
