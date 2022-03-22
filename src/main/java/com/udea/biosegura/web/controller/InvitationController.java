package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.service.InvitationService;
import com.udea.biosegura.domain.service.PlaceService;
import com.udea.biosegura.models.CreateInvitationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/invitations")
public class InvitationController {

    public static final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

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
        InvitationDTO invitationDto = new InvitationDTO();
        invitationDto.setUserId(invitationInput.getUserId());
        invitationDto.setPlaceId(invitationInput.getPlaceId());
        try {
            PlaceDTO foundPlace = placeService.getPlace(invitationInput.getPlaceId()).get();
            LocalTime checkInDate = LocalTime.parse(foundPlace.getCheckIn(), localTimeFormatter);
            LocalTime checkOutDate = LocalTime.parse(foundPlace.getCheckOut(), localTimeFormatter);
            LocalDateTime inDate = LocalDateTime.parse(invitationInput.getInDate(), localDateFormatter);
            LocalDateTime outDate = LocalDateTime.parse(invitationInput.getOutDate(), localDateFormatter);

            System.out.println(checkOutDate.getHour()+" "+checkInDate.getHour());
            System.out.println(outDate.getHour()+" "+inDate.getHour());

            if (outDate.isBefore(inDate)) {
                return new ResponseEntity("400 Bad Request: La fecha de salida debe ser posterior a la de entrada", HttpStatus.BAD_REQUEST);
            }

            if (inDate.getYear() != outDate.getYear() ||
                    inDate.getMonth().compareTo(outDate.getMonth()) != 0 ||
                    inDate.getDayOfMonth() != outDate.getDayOfMonth()) {

                return new ResponseEntity("400 Bad Request: La fechas de entrada y salida deben ser en el mismo dia, mes y año", HttpStatus.BAD_REQUEST);

            }
            if ((inDate.getHour() < checkInDate.getHour() || inDate.getHour() > checkOutDate.getHour() ) ||
                    (outDate.getHour() < checkInDate.getHour() || outDate.getHour() > checkOutDate.getHour())) {


                return new ResponseEntity("400 Bad Request: La fechas de entrada y salida estan por fuera del horario del lugar. "+"La apertura es a las "+checkInDate+ ", cierre a las "+checkOutDate, HttpStatus.BAD_REQUEST);
            } else {
                invitationDto.setInDate(inDate);
                invitationDto.setOutDate(outDate);
                return new ResponseEntity<>(invitationService.save(invitationDto), HttpStatus.CREATED);
            }

        } catch (DateTimeParseException ex) {
            Logger.getLogger(InvitationController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity("400 Bad Request: El formato de " +
                    "fecha ingresada no es valido (yyyy-MM-dd HH:mm)", HttpStatus.BAD_REQUEST);
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
