package ru.example.service.dish;

import ru.example.model.dto.dish.DishDto;
import ru.example.model.dto.dish.NewDishDto;

public interface DishService {
    DishDto add(NewDishDto newDishDto);
}
