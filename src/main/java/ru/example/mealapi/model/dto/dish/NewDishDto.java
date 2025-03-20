package ru.example.mealapi.model.dto.dish;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewDishDto {
    @NotBlank(message = "Error: must not be blank. Value: ${validatedValue}")
    private String name;
    @NotNull(message = "Error: value can't be null")
    @PositiveOrZero(message = "Error: value must be positive or zero. Value: ${validatedValue}")
    private int kkal;
    @PositiveOrZero(message = "Error: value must be positive or zero. Value: ${validatedValue}")
    private double proteins;
    @PositiveOrZero(message = "Error: value must be positive or zero. Value: ${validatedValue}")
    private double fats;
    @PositiveOrZero(message = "Error: value must be positive or zero. Value: ${validatedValue}")
    private double carbohydrates;
}
