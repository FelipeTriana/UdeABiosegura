package com.udea.biosegura.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @Column(name = "id_place")
    private String idPlace;

    @Column(name = "name_place")
    private String namePlace;
    private String phone;
    private String address;
    private String checkIn;
    private String checkOut;
    private int capacity;
    private int actualCapacity;
    private String imgUrl;

    @OneToMany(mappedBy = "place",  cascade = {CascadeType.ALL})
    private List<Invitation> invitations;

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getActualCapacity() {
        return actualCapacity;
    }

    public void setActualCapacity(int actualCapacity) {
        this.actualCapacity = actualCapacity;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String getImgUrl(){
        return this.imgUrl;
    }
}
