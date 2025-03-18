package ru.example.model.mapper;

import ru.example.model.dto.user.NewUserDto;
import ru.example.model.dto.user.UserDto;
import ru.example.model.entity.User;


public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getWeight(),
                user.getHeight(),
                user.getTarget());
    }

    public static User toEntity(NewUserDto newUserDto) {
        if (newUserDto == null) return null;
        return new User(null,
                newUserDto.getName(),
                newUserDto.getEmail(),
                newUserDto.getAge(),
                newUserDto.getWeight(),
                newUserDto.getHeight(),
                newUserDto.getTarget().toString());
    }

    public static User toEntity(Long id, NewUserDto newUserDto) {
        if (newUserDto == null) return null;
        return new User(id,
                newUserDto.getName(),
                newUserDto.getEmail(),
                newUserDto.getAge(),
                newUserDto.getWeight(),
                newUserDto.getHeight(),
                newUserDto.getTarget().toString());
    }

}
