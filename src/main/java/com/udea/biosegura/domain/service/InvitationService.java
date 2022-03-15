package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.repository.InvitationRepository;
import com.udea.biosegura.domain.repository.PlaceRepository;
import com.udea.biosegura.domain.repository.UserRepository;
import com.udea.biosegura.persistence.entity.Invitation;
import com.udea.biosegura.persistence.entity.InvitationPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<InvitationDTO> getAll(){return invitationRepository.getAll();}

    public Optional<InvitationDTO> getInvitation(Integer invitationId){return invitationRepository.getInvitation(invitationId);}

    //public Optional<List<InvitationDTO>> getInvitationByDate(String date){return invitationRepository.getInvitationByDate(date);}

    public InvitationDTO save(InvitationDTO invitationdto){

        UserDTO foundUser = userRepository.getUser(invitationdto.getUserId()).get();
        PlaceDTO foundPlace = placeRepository.getPlace(invitationdto.getPlaceId()).get();
        List<InvitationDTO> currentElements = invitationRepository.getAll();

        if(currentElements.equals(Collections.emptyList())){
            Integer iterable = 1;
            invitationdto.setInvitationId(iterable);
        }else{
            InvitationDTO lastElement = currentElements.get(currentElements.size() - 1);
            invitationdto.setInvitationId(lastElement.getInvitationId()+1);
        }

        //Asignamos los objetos user y place a los atributos nulos del invitationDTO
        invitationdto.setUser(foundUser);
        invitationdto.setPlace(foundPlace);

        if(foundPlace.getActualCapacity() > 0){
            foundPlace.setActualCapacity(foundPlace.getActualCapacity() - 1); //Reducimos capacidad del lugar
            placeRepository.save(foundPlace);
            invitationdto.setPlace(foundPlace); //Reemplazamos el place con el actualizado
            return invitationRepository.save(invitationdto);
        } else {
            throw new IllegalArgumentException("The place has no capacity"); //Pensar en que devolver en caso de que no sea valido
        }
    }

    public boolean delete(Integer invitationId){

        InvitationDTO foundInvitation = invitationRepository.getInvitation(invitationId).get();
        PlaceDTO relatedPlace = placeRepository.getPlace(foundInvitation.getPlaceId()).get();

        if(relatedPlace.getActualCapacity() > relatedPlace.getCapacity()){
            throw new IllegalArgumentException("The place  actual capacity can not be greater than the total capacity");
        } else {
            relatedPlace.setActualCapacity( relatedPlace.getActualCapacity() + 1);
            placeRepository.save(relatedPlace);
        }

        return getInvitation(invitationId).map(inv -> {
            invitationRepository.delete(invitationId);
            return true;
        }).orElse(false);
    }


    public boolean deleteByPlace(String placeId){

        List<InvitationDTO> currentInvitations = invitationRepository.getAll();

        InvitationDTO foundInvitation = currentInvitations.
                stream().
                filter(invitation -> invitation.getPlaceId().equals(placeId)).
                findAny().
                get();

        PlaceDTO foundPlace = foundInvitation.getPlace();

        if(foundPlace.getActualCapacity() > foundPlace.getCapacity()){
            throw new IllegalArgumentException("The place  actual capacity can not be greater than the total capacity");
        } else {
            foundPlace.setActualCapacity( foundPlace.getActualCapacity() + 1);
            placeRepository.save(foundPlace);
        }

        return placeRepository.getPlace(placeId).map(inv -> {
            invitationRepository.deleteByPlace(placeId);
            return true;
        }).orElse(false);
    }



}
