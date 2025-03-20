package ru.example.mealapi.model.dto.dish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {
    private Long id;
    private String name;
    private int kkal;
    private double proteins;
    private double fats;
    private double carbohydrates;
}
