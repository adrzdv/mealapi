package ru.example.mealapi.service.dish;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.mealapi.model.dto.dish.DishDto;
import ru.example.mealapi.model.dto.dish.NewDishDto;
import ru.example.mealapi.model.mapper.DishMapper;
import ru.example.mealapi.repo.DishRepository;

@Service
@AllArgsConstructor
public class DishServiceImp implements DishService {

    @Autowired
    private final DishRepository dishRepository;

    @Override
    public DishDto add(NewDishDto newDishDto) {
        return DishMapper.toDto(
                dishRepository.save(DishMapper.toEntity(newDishDto)));
    }
}
