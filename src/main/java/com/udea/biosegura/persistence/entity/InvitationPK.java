package com.udea.biosegura.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
public class InvitationPK implements Serializable {

    @Column(name = "id_place")
    private String placeid;

    @Column(name = "id_user")
    private String userid;

    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Problema aquí, se esta mapeando desde el dominion y si no lo definimos le lleva un nulo
    @Column(name = "id_invitation")
    private Integer invitation;

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getInvitationid() {
        return invitation;
    }

    public void setInvitationid(Integer invitationId) {
        this.invitation = invitationId;
    }
}
