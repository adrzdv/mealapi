package ru.example.mealapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.mealapi.model.dto.dish.DishDto;
import ru.example.mealapi.model.dto.dish.NewDishDto;
import ru.example.mealapi.service.dish.DishService;

@RestController
@RequestMapping(value = "dish")
@AllArgsConstructor
public class DishController {

    @Autowired
    private final DishService dishService;

    @PostMapping
    public DishDto add(@Valid @RequestBody NewDishDto newDishDto) {
        return dishService.add(newDishDto);
    }
}
