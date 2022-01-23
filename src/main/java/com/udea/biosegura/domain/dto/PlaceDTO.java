package com.udea.biosegura.domain.dto;

import java.util.List;

public class PlaceDTO {

    private String idPlace;
    private String namePlace;
    private String phone;
    private String address;
    private int capacity;
    //private List<InvitationDTO> invitations;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /*
    public List<InvitationDTO> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<InvitationDTO> invitations) {
        this.invitations = invitations;
    }
     */
}
