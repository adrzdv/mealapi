package ru.example.service.dish;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.model.dto.dish.DishDto;
import ru.example.model.dto.dish.NewDishDto;
import ru.example.model.mapper.DishMapper;
import ru.example.repo.DishRepository;

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
