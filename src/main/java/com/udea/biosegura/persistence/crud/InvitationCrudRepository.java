package com.udea.biosegura.persistence.crud;

import com.udea.biosegura.persistence.entity.Invitation;
import com.udea.biosegura.persistence.entity.InvitationPK;
import org.springframework.data.repository.CrudRepository;

public interface InvitationCrudRepository extends CrudRepository<Invitation, InvitationPK> {
}
