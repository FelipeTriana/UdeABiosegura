package com.udea.biosegura.persistence.mapper;

import com.udea.biosegura.domain.dto.UserDTO;
import com.udea.biosegura.persistence.entity.User;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InvitationMapper.class})
public interface UserMapper {

    @Mappings({

    })
    UserDTO toUserDTO(User user);
    List<UserDTO> toUsersDTO(List<User> users);

    @InheritInverseConfiguration
    @Mapping(target = "invitations", ignore = true)
    User toUser(UserDTO userdto);


}
