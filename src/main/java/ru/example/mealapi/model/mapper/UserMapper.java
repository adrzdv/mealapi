package ru.example.mealapi.model.mapper;

import ru.example.mealapi.model.dto.user.NewUserDto;
import ru.example.mealapi.model.dto.user.UserDto;
import ru.example.mealapi.model.entity.User;

/**
 * Класс UserMapper предназначен для преобразования сущностей User и NewUserDto в DTO и обратно.
 */
public class UserMapper {

    /**
     * Преобразует сущность User в DTO.
     *
     * @param user объект User
     * @return объект UserDto или null, если user равен null
     */
    public static UserDto toDto(User user) {
        if (user == null) return null;
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getWeight(),
                user.getHeight(),
                user.getTarget(),
                user.getActivityType(),
                user.getGender());
    }

    /**
     * Преобразует DTO NewUserDto в сущность User.
     *
     * @param newUserDto объект NewUserDto
     * @return объект User или null, если newUserDto равен null
     */
    public static User toEntity(NewUserDto newUserDto) {
        if (newUserDto == null) return null;
        return new User(null,
                newUserDto.getName(),
                newUserDto.getEmail(),
                newUserDto.getAge(),
                newUserDto.getWeight(),
                newUserDto.getHeight(),
                newUserDto.getTarget().toString(),
                newUserDto.getActivityType().toString(),
                newUserDto.getGender());
    }

}
