package ru.example.mealapi.model.mapper;

import ru.example.mealapi.model.dto.dish.DishDto;
import ru.example.mealapi.model.dto.dish.NewDishDto;
import ru.example.mealapi.model.entity.Dish;

/**
 * Маппер для преобразования между сущностью {@link Dish} и DTO-объектами {@link DishDto} и {@link NewDishDto}.
 */
public class DishMapper {

    /**
     * Преобразует DTO {@link NewDishDto} в сущность {@link Dish}.
     *
     * @param newDishDto объект DTO, содержащий данные нового блюда
     * @return объект {@link Dish}, созданный на основе переданного DTO, или {@code null}, если входной параметр равен {@code null}
     */
    public static Dish toEntity(NewDishDto newDishDto) {
        if (newDishDto == null) return null;
        return new Dish(null,
                newDishDto.getName(),
                newDishDto.getKkal(),
                newDishDto.getProteins(),
                newDishDto.getFats(),
                newDishDto.getCarbohydrates());
    }

    /**
     * Преобразует сущность {@link Dish} в DTO {@link DishDto}.
     *
     * @param dish объект сущности блюда
     * @return объект {@link DishDto}, содержащий данные блюда, или {@code null}, если входной параметр равен {@code null}
     */
    public static DishDto toDto(Dish dish) {
        if (dish == null) return null;
        return new DishDto(dish.getId(),
                dish.getName(),
                dish.getKkal(),
                dish.getProteins(),
                dish.getFats(),
                dish.getCarbohydrates());
    }
}
