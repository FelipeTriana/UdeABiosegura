package com.udea.biosegura.persistence.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "invitations")
public class Invitation {

    @EmbeddedId   //Composite primary key
    private InvitationPK id;

    private String inDate;
    private String outDate;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("idPlace")
    @JoinColumn(name = "id_place", insertable = false, updatable = false)
    private Place place;

    public InvitationPK getId() {
        return id;
    }

    public void setId(InvitationPK id) {
        this.id = id;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
