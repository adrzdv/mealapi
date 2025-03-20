package ru.example.mealapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.mealapi.model.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
