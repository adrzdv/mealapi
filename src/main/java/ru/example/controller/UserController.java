package ru.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.model.dto.user.NewUserDto;
import ru.example.model.dto.user.UserDto;
import ru.example.service.user.UserService;

@RestController
@RequestMapping(value = "user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto add(@Valid @RequestBody NewUserDto newUserDto) {
        return userService.add(newUserDto);
    }
}
