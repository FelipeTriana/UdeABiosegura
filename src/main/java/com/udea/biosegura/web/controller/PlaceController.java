package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            Date h1 = new SimpleDateFormat("HH:mm")
                    .parse(placedto.getCheckIn());
            Date h2 = new SimpleDateFormat("HH:mm")
                    .parse(placedto.getCheckOut());
            if(h2.after(h1)){
                return new ResponseEntity<>(placeService.save(placedto), HttpStatus.CREATED);
            }else{
                return new ResponseEntity("400 Bad Request: Las horas de entrada y salida " +
                        "no concuerdan", HttpStatus.BAD_REQUEST);
            }
        } catch (ParseException ex) {
            Logger.getLogger(PlaceController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity("400 Bad Request: El formato de " +
                    "hora ingresado no es valido", HttpStatus.BAD_REQUEST);
        }
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
