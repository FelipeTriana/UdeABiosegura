package com.udea.biosegura.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InvitationInput {
    private String userId;
    private String placeId;
    private String inDate;
    private String outDate;
}
