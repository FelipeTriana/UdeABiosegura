package com.udea.biosegura.persistence.crud;

import com.udea.biosegura.persistence.entity.Invitation;
import com.udea.biosegura.persistence.entity.InvitationPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface InvitationCrudRepository extends CrudRepository<Invitation, InvitationPK> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from invitations i where i.id_invitation = ?", nativeQuery = true)
    void deleteByIdInvitation(Integer invitation);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from invitations i where i.id_place = ?", nativeQuery = true)
    int deleteByPlace(String placeid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from invitations i where i.outDate = ?", nativeQuery = true)
    int deleteByDate(String date);

    Optional<Invitation> findByIdInvitation(Integer invitationId);

    List<Invitation> findByOutDate(String Date);  //Revisar
}
