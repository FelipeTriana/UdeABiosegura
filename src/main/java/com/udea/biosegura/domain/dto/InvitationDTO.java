package com.udea.biosegura.domain.dto;

import java.util.Date;

public class InvitationDTO {

    private Integer invitationId;
    private String userId;
    private String placeId;
    private Date inDate;
    private Date outDate;
    private UserDTO user;
    private PlaceDTO place;

    public InvitationDTO() {
    }

    public InvitationDTO(String userId, String placeId, Date inDate, Date outDate, Integer invitationId) {
        this.invitationId = invitationId;
        this.userId = userId;
        this.placeId = placeId;
        this.inDate = inDate;
        this.outDate = outDate;
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

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
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

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }
}
