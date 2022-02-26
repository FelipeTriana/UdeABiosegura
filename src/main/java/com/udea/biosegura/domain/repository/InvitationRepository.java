package com.udea.biosegura.domain.repository;


import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.persistence.entity.InvitationPK;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository {

    List<InvitationDTO> getAll();
    Optional<InvitationDTO> getInvitation(Integer invitationId);
    //Optional<List<InvitationDTO>> getInvitationByDate(String date);
    InvitationDTO save(InvitationDTO invitationdto);
    String deleteByPlace(String placeid);
    String delete(Integer invitationId);
    int deleteByDate(String placeid);



}
