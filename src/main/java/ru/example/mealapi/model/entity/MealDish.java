package ru.example.mealapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meal_dishes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_meal", nullable = false)
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "id_dish", nullable = false)
    private Dish dish;

    @Column(nullable = false)
    private double portionSize;
}
