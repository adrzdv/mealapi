package ru.example.mealapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.example.mealapi.model.entity.MealDish;

import java.time.LocalDate;
import java.util.List;

public interface MealDishRepository extends JpaRepository<MealDish, Long> {

    @Query("SELECT md FROM MealDish md JOIN md.meal m WHERE m.mealDate = :currDate AND m.user.id = :userId")
    List<MealDish> getMealDishesInDate(LocalDate currDate, Long userId);



}
