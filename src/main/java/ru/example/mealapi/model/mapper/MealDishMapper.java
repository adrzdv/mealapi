package ru.example.mealapi.model.mapper;


import ru.example.mealapi.model.dto.mealdish.MealDishDto;
import ru.example.mealapi.model.dto.mealdish.NewMealDishDto;
import ru.example.mealapi.model.entity.Dish;
import ru.example.mealapi.model.entity.Meal;
import ru.example.mealapi.model.entity.MealDish;

/**
 * Маппер для преобразования между сущностью {@link MealDish} и DTO-объектами {@link MealDishDto} и {@link NewMealDishDto}.
 */
public class MealDishMapper {

    /**
     * Преобразует DTO {@link NewMealDishDto} в сущность {@link MealDish}.
     *
     * @param newMealDishDto объект DTO, содержащий данные нового блюда в приёме пищи
     * @param meal объект {@link Meal}, представляющий приём пищи
     * @param dish объект {@link Dish}, представляющий блюдо
     * @return объект {@link MealDish}, созданный на основе переданных данных, или {@code null}, если входной параметр равен {@code null}
     */
    public static MealDish toEntity(NewMealDishDto newMealDishDto, Meal meal, Dish dish) {
        if (newMealDishDto == null) return null;
        return new MealDish(null,
                meal,
                dish,
                newMealDishDto.getPortionSize()
        );
    }

    /**
     * Преобразует сущность {@link MealDish} в DTO {@link MealDishDto}.
     *
     * @param mealDish объект сущности блюда в приёме пищи
     * @return объект {@link MealDishDto}, содержащий данные о блюде в приёме пищи, или {@code null}, если входной параметр равен {@code null}
     */
    public static MealDishDto toDto(MealDish mealDish) {
        if (mealDish == null) return null;
        return new MealDishDto(mealDish.getId(),
                mealDish.getMeal().getId(),
                mealDish.getDish(),
                mealDish.getPortionSize());
    }
}
