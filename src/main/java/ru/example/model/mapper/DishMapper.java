package ru.example.model.mapper;

import ru.example.model.dto.dish.DishDto;
import ru.example.model.dto.dish.NewDishDto;
import ru.example.model.entity.Dish;

public class DishMapper {

    public static Dish toEntity(NewDishDto newDishDto) {
        if (newDishDto == null) return null;
        return new Dish(null,
                newDishDto.getName(),
                newDishDto.getKkal(),
                newDishDto.getProteins(),
                newDishDto.getFats(),
                newDishDto.getCarbohydrates());
    }

    public static DishDto toDto(Dish dish) {
        if (dish == null) return null;
        return new DishDto(dish.getId(),
                dish.getName(),
                dish.getKkal(),
                dish.getProteins(),
                dish.getFats(),
                dish.getCarbohydrates());
    }

    public static Dish toEntity(Long id, NewDishDto newDishDto) {
        if (newDishDto == null) return null;
        return new Dish(id,
                newDishDto.getName(),
                newDishDto.getKkal(),
                newDishDto.getProteins(),
                newDishDto.getFats(),
                newDishDto.getCarbohydrates());
    }
}
