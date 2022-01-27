package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.repository.InvitationRepository;
import com.udea.biosegura.domain.repository.PlaceRepository;
import com.udea.biosegura.persistence.entity.InvitationPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public List<InvitationDTO> getAll(){return invitationRepository.getAll();}

    public Optional<InvitationDTO> getInvitation(InvitationPK idInvitation){return invitationRepository.getInvitation(idInvitation);}

    public InvitationDTO save(InvitationDTO invitationdto){
        /* Se agenda una visita
        *
        *  Un user asistira a un place en una hora_in hasta hora_out
        *
        *  Se carga el place a actualizar
        *
        *  El place debe reducir su capacidad en 1 (SI place.capacidad > 0)
        *
        *  Si place.capacidad == 0 -> Rechazo de invitacion
        *
        *  Actualizar el place en BD
        *
        *  Asignar el place al invitationDTO
        * */

        PlaceDTO foundPlace = placeRepository.getPlace(invitationdto.getPlace().getIdPlace()).get();

        if(foundPlace.getCapacity() > 0){
            foundPlace.setCapacity(foundPlace.getCapacity() - 1); //Reducimos capacidad del lugar
            placeRepository.save(foundPlace);
            invitationdto.setPlace(foundPlace);
            return invitationRepository.save(invitationdto);
        } else {
            throw new IllegalArgumentException("The place has no capacity"); //Pensar en que devolver
        }
    }

    public boolean delete(InvitationPK idInvitation){
        return getInvitation(idInvitation).map(inv -> {
            invitationRepository.delete(idInvitation);
            return true;
        }).orElse(false);
    }
}
