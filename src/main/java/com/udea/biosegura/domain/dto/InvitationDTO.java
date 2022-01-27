package com.udea.biosegura.domain.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvitationDTO {

    private String userId;
    private String placeId;
    private String in_date;
    private String out_date;
    private UserDTO user;
    private PlaceDTO place;

    public InvitationDTO(String userId, String placeId, String in_date, String out_date) {
        this.userId = userId;
        this.placeId = placeId;
        this.in_date = in_date;
        this.out_date = out_date;
    }

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
