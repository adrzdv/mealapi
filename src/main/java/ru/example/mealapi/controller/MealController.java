package ru.example.mealapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.mealapi.exception.NotFoundCustomException;
import ru.example.mealapi.model.dto.UserDataReportDto;
import ru.example.mealapi.model.dto.mealdish.MealDishDto;
import ru.example.mealapi.model.dto.mealdish.NewMealDishDto;
import ru.example.mealapi.service.meal.MealService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/meal")
@AllArgsConstructor
public class MealController {

    @Autowired
    private final MealService mealService;

    @PostMapping(value = "/{idMeal}")
    public List<MealDishDto> addDishes(@PathVariable long idMeal,
                           @Valid @RequestBody List<NewMealDishDto> newMealDishDto,
                           @RequestHeader("X-User-Id") long userId) throws NotFoundCustomException {
        return mealService.addDishes(idMeal, userId, newMealDishDto);
    }

    @GetMapping(value = "/{idUser}")
    public UserDataReportDto getUsersMealsInDay(@PathVariable long idUser,
                                                @RequestParam LocalDate date) {
        return mealService.getUsersReportByDay(date, idUser);
    }

    @GetMapping(value = "/{idUser}/check")
    public boolean checkUsersTarget(@PathVariable long idUser,
                                    @RequestParam LocalDate date) throws NotFoundCustomException {
        return mealService.checkUsersTarget(idUser, date);
    }

    @GetMapping(value = "/{idUser}/detailed")
    public List<MealDishDto> getUsersMeals(@PathVariable long idUser,
                                           @RequestParam LocalDate date) throws NotFoundCustomException {
        return mealService.getUsersDailyMeals(idUser, date);
    }

}
