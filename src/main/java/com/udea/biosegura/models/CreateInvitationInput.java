package com.udea.biosegura.models;

public class CreateInvitationInput {
    private String userId;
    private String placeId;
    private String inDate;
    private String outDate;

    public CreateInvitationInput() {

    }

    public CreateInvitationInput(String userId, String placeId, String inDate, String outDate) {
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
}
