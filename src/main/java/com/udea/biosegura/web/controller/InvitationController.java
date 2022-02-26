package com.udea.biosegura.web.controller;

import com.udea.biosegura.Pruebita;
import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.service.InvitationService;
import com.udea.biosegura.domain.service.PlaceService;
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

    @Autowired
    private PlaceService placeService;

   /* @Autowired
    private InvitationDTO invitationdto;   //Para probar ahora*/

    @GetMapping()
    public ResponseEntity<List<InvitationDTO>> getAll() {
        return new ResponseEntity<>(invitationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idInvitation}")
    public ResponseEntity<InvitationDTO> getInvitation(@PathVariable("idInvitation") Integer id) {
        InvitationDTO test = invitationService.getInvitation(id).get();
        System.out.println("Así se ve: "+test.getInDate()+" "+test.getOutDate());
        return invitationService.getInvitation(id)
                .map(inv -> new ResponseEntity<>(inv, HttpStatus.OK))
                .orElse(new ResponseEntity("404 not found", HttpStatus.NOT_FOUND)); //Se quito <>
    }



    @PostMapping()
    public ResponseEntity<InvitationDTO> save(@RequestBody CreateInvitationInput invitationInput) {
        InvitationDTO invitationdto = new InvitationDTO();  //Es bueno instanciar así? no viola inyeccion de dependencias?
        invitationdto.setUserId(invitationInput.getUserId());
        invitationdto.setPlaceId(invitationInput.getPlaceId());
        try {
            PlaceDTO foundPlace = placeService.getPlace(invitationInput.getPlaceId()).get();
            Date currentDate = new Date();

            Date outDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(invitationInput.getOutDate());
            String InDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(currentDate);

            String checkInst = InDate;  //fecha de entrada y salida deben ser en el mismo dia
            String checkOutst = InDate;
            checkInst = checkInst.replace(checkInst.substring(11,16), foundPlace.getCheckIn());
            System.out.println(checkInst);
            checkOutst = checkOutst.replace(checkOutst.substring(11,16), foundPlace.getCheckOut());
            System.out.println(checkOutst);

            Date checkIn = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(checkInst);
            Date checkOut = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(checkOutst);

            if (outDate.after(currentDate) && outDate.after(checkIn) && outDate.before(checkOut)) {
                String inputInDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(currentDate);
                String inputOutDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(outDate);
                invitationdto.setInDate(inputInDate);
                invitationdto.setOutDate(inputOutDate);
                return new ResponseEntity<>(invitationService.save(invitationdto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity("400 Bad Request: La fecha de salida ingresada no es " +
                        "valida, verifique el horario de apertura y cierre del lugar", HttpStatus.BAD_REQUEST);
            }
        } catch (ParseException ex) {
            Logger.getLogger(InvitationController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity("400 Bad Request: El formato de " +
                    "fecha ingresado no es valido", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{idInvitation}")
    public ResponseEntity<InvitationDTO> delete(@PathVariable("idInvitation") Integer id) {
        if (invitationService.delete(id)) {
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
