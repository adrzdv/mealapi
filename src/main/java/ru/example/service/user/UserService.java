package ru.example.service.user;

import ru.example.model.dto.user.NewUserDto;
import ru.example.model.dto.user.UserDto;

public interface UserService {
    UserDto add(NewUserDto newUserDto);
}
