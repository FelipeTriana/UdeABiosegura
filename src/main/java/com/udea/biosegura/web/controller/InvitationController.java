package com.udea.biosegura.web.controller;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.service.InvitationService;
import com.udea.biosegura.persistence.entity.InvitationPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitations")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @GetMapping()
    public ResponseEntity<List<InvitationDTO>> getAll() {
        return new ResponseEntity<>(invitationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idInvitation}")
    public ResponseEntity<InvitationDTO> getUser(@PathVariable("idInvitation") InvitationPK id) {
        return invitationService.getInvitation(id)
                .map(inv-> new ResponseEntity<>(inv, HttpStatus.OK))
                .orElse(new ResponseEntity("404 not found",HttpStatus.NOT_FOUND)); //Se quito <>
    }

    @PostMapping()
    public ResponseEntity<InvitationDTO> save(@RequestBody InvitationDTO invitationdto) {
        return new ResponseEntity<>(invitationService.save(invitationdto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idInvitation}")
    public ResponseEntity<InvitationDTO> delete(@PathVariable("idInvitation") InvitationPK id) {
        if (invitationService.delete(id)) {
            return new ResponseEntity("Delete Successfuly",HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
