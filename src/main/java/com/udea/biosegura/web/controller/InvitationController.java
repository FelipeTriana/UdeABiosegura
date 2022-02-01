package com.udea.biosegura.web.controller;

import com.udea.biosegura.Pruebita;
import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.service.InvitationService;
import com.udea.biosegura.models.CreateInvitationInput;
import com.udea.biosegura.persistence.entity.InvitationPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/invitations")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

   /* @Autowired
    private InvitationDTO invitationdto;   //Para probar ahora*/

    @GetMapping()
    public ResponseEntity<List<InvitationDTO>> getAll() {
        return new ResponseEntity<>(invitationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idInvitation}")
    public ResponseEntity<InvitationDTO> getInvitation(@PathVariable("idInvitation") Integer id) {
        return invitationService.getInvitation(id)
                .map(inv -> new ResponseEntity<>(inv, HttpStatus.OK))
                .orElse(new ResponseEntity("404 not found", HttpStatus.NOT_FOUND)); //Se quito <>
    }

    @PostMapping()
    public ResponseEntity<InvitationDTO> save(@RequestBody CreateInvitationInput invitationInput) {
        InvitationDTO invitationdto = new InvitationDTO();  //Es bueno instanciar así? no viola inyeccion de dependencias?
        //invitationdto.setInvitationId(invitationInput.getInvitationId());
        invitationdto.setUserId(invitationInput.getUserId());
        invitationdto.setPlaceId(invitationInput.getPlaceId());
        try {
            Date inDate = new SimpleDateFormat("dd/MM/yyyy HH:mm")
                    .parse(invitationInput.getInDate());
            Date outDate = new SimpleDateFormat("dd/MM/yyyy HH:mm")
                    .parse(invitationInput.getOutDate());
            Date currentDate = new Date();
            if (inDate.after(currentDate) && outDate.after(inDate)) {
                invitationdto.setInDate(inDate);
                invitationdto.setOutDate(outDate);
                return new ResponseEntity<>(invitationService.save(invitationdto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity("400 Bad Request: Las fechas ingresadas no concuerdan " +
                        "entre si o con la fecha actual del sistema", HttpStatus.BAD_REQUEST);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Pruebita.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity("400 Bad Request: El formato de " +
                    "fecha ingresado no es valido", HttpStatus.BAD_REQUEST);
        }


        //return new ResponseEntity<>(invitationService.save(invitationdto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idInvitation}")
    public ResponseEntity<InvitationDTO> delete(@PathVariable("idInvitation") Integer id) {
        if (invitationService.delete(id)) {
            return new ResponseEntity("Delete Successfuly", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("byuser/{userid}")
    public ResponseEntity<InvitationDTO> deleteByUser(@PathVariable("userid") String id) {
        if (invitationService.deleteByUser(id)) {
            return new ResponseEntity("Delete Successfuly", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("byplace/{placeid}")
    public ResponseEntity<InvitationDTO> deleteByPlace(@PathVariable("placeid") String id) {
        if (invitationService.deleteByPlace(id)) {
            return new ResponseEntity("Delete Successfuly", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
