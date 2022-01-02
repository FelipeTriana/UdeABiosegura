package com.udea.biosegura.domain.dto;

public class InvitationDTO {

    private String idInvitation;
    private String in_date;
    private String out_date;
    private UserDTO user;
    private PlaceDTO place;

    public String getIdInvitation() {
        return idInvitation;
    }

    public void setIdInvitation(String idInvitation) {
        this.idInvitation = idInvitation;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PlaceDTO getPlace() {
        return place;
    }

    public void setPlace(PlaceDTO place) {
        this.place = place;
    }
}
