package com.udea.biosegura.persistence.repository;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.repository.InvitationRepository;
import com.udea.biosegura.persistence.crud.InvitationCrudRepository;
import com.udea.biosegura.persistence.entity.Invitation;
import com.udea.biosegura.persistence.entity.InvitationPK;
import com.udea.biosegura.persistence.mapper.InvitationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InvitationPersistenceRepository implements InvitationRepository {

    @Autowired
    private InvitationCrudRepository invitationCrudRepository;

    @Autowired
    private InvitationMapper mapper;

    @Override
    public List<InvitationDTO> getAll() {
        List<Invitation> invitations = (List<Invitation>) invitationCrudRepository.findAll(); //Ready
        return mapper.toInvitationsDTO(invitations);
    }

    @Override
    public Optional<InvitationDTO> getInvitation(Integer idInvitation) {
        Optional<Invitation> invitation = invitationCrudRepository.findByIdInvitation(idInvitation);  //Ready
        return  invitation.map(usr -> mapper.toInvitationDTO(usr));
    }

    @Override
    public InvitationDTO save(InvitationDTO invitationdto) {
        Invitation invitation = mapper.toInvitation(invitationdto);
        return mapper.toInvitationDTO(invitationCrudRepository.save(invitation));      //Ready
    }

    @Override
    public String deleteByUser(String userid) {
        invitationCrudRepository.deleteByUser(userid);                 //Ready
        System.out.println("Success");
        return "Success";
    }

    @Override
    public String deleteByPlace(String placeid) {
        invitationCrudRepository.deleteByPlace(placeid);                 //Ready
        System.out.println("Success");
        return "Success";
    }

    @Override
    public String delete(Integer invitation) {
        invitationCrudRepository.deleteByIdInvitation(invitation);                 //Ready
        System.out.println("Success");
        return "Success";
    }



}
