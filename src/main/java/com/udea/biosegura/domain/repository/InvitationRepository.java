package com.udea.biosegura.domain.repository;


import com.udea.biosegura.domain.dto.InvitationDTO;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository {

    List<InvitationDTO> getAll();
    Optional<InvitationDTO> getInvitation(String idInvitation);
    InvitationDTO save(InvitationDTO invitation);
    void delete(String idInvitation);


}
