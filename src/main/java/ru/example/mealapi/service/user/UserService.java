package ru.example.mealapi.service.user;

import ru.example.mealapi.model.dto.user.NewUserDto;
import ru.example.mealapi.model.dto.user.UserDto;

/**
 * Сервис для управления пользователями.
 * Определяет методы для создания и работы с пользователями.
 */
public interface UserService {
    /**
     * Добавляет нового пользователя.
     *
     * @param newUserDto данные нового пользователя в формате {@link NewUserDto}
     * @return объект {@link UserDto} пользователя с присвоенным идентификатором
     */
    UserDto add(NewUserDto newUserDto);
}
