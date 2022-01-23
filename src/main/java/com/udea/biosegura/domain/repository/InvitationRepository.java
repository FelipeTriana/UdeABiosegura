package com.udea.biosegura.domain.repository;


import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.persistence.entity.InvitationPK;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository {

    List<InvitationDTO> getAll();
    Optional<InvitationDTO> getInvitation(InvitationPK idInvitation);
    InvitationDTO save(InvitationDTO invitationdto);
    String delete(InvitationPK idInvitation);


}
