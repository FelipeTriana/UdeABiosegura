package com.udea.biosegura.domain.dto;

public class InvitationDTO {

    //private int id;
    private String userId;
    private String placeId;
    private String in_date;
    private String out_date;
    //private PlaceDTO place;
    //private UserDTO user;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
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

   /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/


   /* public PlaceDTO getPlace() {
        return place;
    }

    public void setPlace(PlaceDTO place) {
        this.place = place;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }*/
}
