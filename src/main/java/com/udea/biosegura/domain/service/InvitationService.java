package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.domain.repository.InvitationRepository;
import com.udea.biosegura.domain.repository.PlaceRepository;
import com.udea.biosegura.domain.repository.UserRepository;
import com.udea.biosegura.persistence.entity.InvitationPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Optional<InvitationDTO> getInvitation(Integer idInvitation){return invitationRepository.getInvitation(idInvitation);}

    public InvitationDTO save(InvitationDTO invitationdto){

        UserDTO foundUser = userRepository.getUser(invitationdto.getUserId()).get();
        PlaceDTO foundPlace = placeRepository.getPlace(invitationdto.getPlaceId()).get();

        //Asignamos los objetos user y place a los atributos nulos del invitationDTO
        invitationdto.setUser(foundUser);
        invitationdto.setPlace(foundPlace);

        if(foundPlace.getCapacity() > 0){
            foundPlace.setCapacity(foundPlace.getCapacity() - 1); //Reducimos capacidad del lugar
            placeRepository.save(foundPlace);
            invitationdto.setPlace(foundPlace); //Reemplazamos el place con el actualizado
            return invitationRepository.save(invitationdto);
        } else {
            throw new IllegalArgumentException("The place has no capacity"); //Pensar en que devolver en caso de que no sea valido
        }
    }

    public boolean delete(Integer idInvitation){
        return getInvitation(idInvitation).map(inv -> {
            invitationRepository.delete(idInvitation);
            return true;
        }).orElse(false);
    }

    public boolean deleteByUser(String userid){
        return userRepository.getUser(userid).map(inv -> {
            invitationRepository.deleteByUser(userid);
            return true;
        }).orElse(false);
    }

    public boolean deleteByPlace(String placeid){
        return placeRepository.getPlace(placeid).map(inv -> {
            invitationRepository.deleteByPlace(placeid);
            return true;
        }).orElse(false);
    }

}
