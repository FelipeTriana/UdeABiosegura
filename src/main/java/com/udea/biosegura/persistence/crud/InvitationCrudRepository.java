package com.udea.biosegura.persistence.crud;

import com.udea.biosegura.persistence.entity.Invitation;
import com.udea.biosegura.persistence.entity.InvitationPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;


public interface InvitationCrudRepository extends CrudRepository<Invitation, InvitationPK> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from invitations i where i.id_invitation = ?", nativeQuery = true)
    void deleteByIdInvitation(Integer invitation);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from invitations i where i.id_user = ?", nativeQuery = true)
    void deleteByUser(String userid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from invitations i where i.id_place = ?", nativeQuery = true)
    void deleteByPlace(String placeid);

    Optional<Invitation> findByIdInvitation(Integer invitationId);
}
