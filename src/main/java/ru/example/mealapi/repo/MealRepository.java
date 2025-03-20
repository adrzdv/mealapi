package ru.example.mealapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.example.mealapi.model.entity.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    @Query("SELECT m FROM Meal m WHERE m.mealDate = :date AND m.user.id = :idUser")
    List<Meal> getUsersMealsInDay(long idUser, LocalDate date);
}
