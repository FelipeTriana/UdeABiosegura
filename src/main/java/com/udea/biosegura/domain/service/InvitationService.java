package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.repository.InvitationRepository;
import com.udea.biosegura.persistence.entity.InvitationPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    public List<InvitationDTO> getAll(){return invitationRepository.getAll();}

    public Optional<InvitationDTO> getInvitation(InvitationPK idInvitation){return invitationRepository.getInvitation(idInvitation);}

    public InvitationDTO save(InvitationDTO invitationdto){return invitationRepository.save(invitationdto);}

    public boolean delete(InvitationPK idInvitation){
        return getInvitation(idInvitation).map(inv -> {
            invitationRepository.delete(idInvitation);
            return true;
        }).orElse(false);
    }
}
