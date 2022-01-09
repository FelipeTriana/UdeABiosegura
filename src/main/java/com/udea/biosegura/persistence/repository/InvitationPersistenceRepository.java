package com.udea.biosegura.persistence.repository;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.domain.repository.InvitationRepository;

import java.util.List;
import java.util.Optional;

public class InvitationPersistenceRepository implements InvitationRepository {
    @Override
    public List<InvitationDTO> getAll() {
        return null;
    }

    @Override
    public Optional<InvitationDTO> getInvitation(String idInvitation) {
        return Optional.empty();
    }

    @Override
    public InvitationDTO save(InvitationDTO invitation) {
        return null;
    }

    @Override
    public void delete(String idInvitation) {

    }
}
