package com.udea.biosegura.persistence.mapper;

import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.persistence.entity.Place;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InvitationMapper.class})
public interface PlaceMapper {

    @Mappings({

    })
    PlaceDTO toPlaceDTO(Place place);
    List<PlaceDTO> toPlacesDTO(List<Place> places);

    @InheritInverseConfiguration
    //@Mapping(target = "invitations", ignore = true)
    Place toPlace(PlaceDTO placedto);

}
