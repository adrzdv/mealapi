package ru.example.mealapi.model.dto.mealdish;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewMealDishDto {
    @NotNull(message = "Error: value can't be null")
    private Long idDish;
    @NotNull(message = "Error: value can't be null")
    @Positive(message = "Error: value must be positive. Value: ${validatedValue}")
    private double portionSize;
}
