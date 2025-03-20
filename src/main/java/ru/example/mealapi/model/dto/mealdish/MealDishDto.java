package ru.example.mealapi.model.dto.mealdish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.mealapi.model.entity.Dish;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealDishDto {
    private Long id;
    private Long idMeal;
    private Dish dish;
    private double portionSize;
}
