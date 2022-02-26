package com.udea.biosegura.persistence.mapper;

import com.udea.biosegura.domain.dto.InvitationDTO;
import com.udea.biosegura.persistence.entity.Invitation;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, PlaceMapper.class})
public interface InvitationMapper {

    @Mappings({
            @Mapping(source = "id.invitationid", target = "invitationId"),
            @Mapping(source = "id.userid", target = "userId"),
            @Mapping(source = "id.placeid", target = "placeId")
    })
    InvitationDTO toInvitationDTO(Invitation invitation);
    List<InvitationDTO> toInvitationsDTO(List<Invitation> invitations);

    @InheritInverseConfiguration
    Invitation toInvitation(InvitationDTO invitationdto);

}
