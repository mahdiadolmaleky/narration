package com.hit.narration.service.mapper;

import com.hit.narration.domain.User;
import com.hit.narration.service.dto.UserDTO;
import com.hit.narration.validation.EncodedMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, PasswordEncoderMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, User> {

    UserDTO toDto(User user);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User toEntity(UserDTO userDTO);

    @Named("to-entity-for-register")
    @Mapping(target = "password",ignore = true)
    User toEntityForRegister(UserDTO userDTO);

    default User fromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
