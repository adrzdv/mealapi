package ru.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.model.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
