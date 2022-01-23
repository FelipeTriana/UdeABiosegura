package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping()
    public ResponseEntity<List<PlaceDTO>> getAll() {
        return new ResponseEntity<>(placeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idPlace}")
    public ResponseEntity<PlaceDTO> getPlace(@PathVariable("idPlace") String id) {
        return placeService.getPlace(id)
                .map(usr-> new ResponseEntity<>(usr, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<PlaceDTO> save(@RequestBody PlaceDTO placedto) {
        return new ResponseEntity<>(placeService.save(placedto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idPlace}")
    public ResponseEntity<PlaceDTO> delete(@PathVariable("idPlace") String id) {
        if (placeService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
