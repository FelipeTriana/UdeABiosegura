package com.udea.biosegura.persistence.entity;

import javax.persistence.*;


@Entity
@Table(name = "invitations")
public class Invitation {

    @EmbeddedId   //Composite primary key
    private InvitationPK id;

    private String in_date;
    private String out_date;

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

    public String getIn_date() {
        return in_date;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }

    public String getOut_date() {
        return out_date;
    }

    public void setOut_date(String out_date) {
        this.out_date = out_date;
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
