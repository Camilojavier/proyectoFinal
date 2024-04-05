package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Users;
import com.camilo.arce.proyecto.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsersMapper implements CustomMapper<UsersDto,Users> {

    @Override
    public UsersDto toDto(Users user) {
        final UsersDto userDto = new UsersDto();
        userDto.setUsername(user.getUsername());
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setHashedPassword(user.getHashedPassword());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    @Override
    public Users toEntity(UsersDto userDto) {
        final Users user = new Users();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setHashedPassword(userDto.getHashedPassword());
        return user;
    }
}
