package ru.example.mealapi.service.dish;

import ru.example.mealapi.model.dto.dish.DishDto;
import ru.example.mealapi.model.dto.dish.NewDishDto;

/**
 * Сервис для управления блюдами.
 * Определяет методы для создания и получения информации о блюдах.
 */
public interface DishService {
    /**
     * Добавляет новое блюдо.
     *
     * @param newDishDto данные нового блюда в формате {@link NewDishDto}
     * @return объект добавленного блюда в формате {@link DishDto}
     */
    DishDto add(NewDishDto newDishDto);
}
