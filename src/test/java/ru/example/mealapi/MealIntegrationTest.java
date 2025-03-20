package ru.example.mealapi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.example.mealapi.exception.NotFoundCustomException;
import ru.example.mealapi.model.dto.UserDataReportDto;
import ru.example.mealapi.model.dto.dish.DishDto;
import ru.example.mealapi.model.dto.dish.NewDishDto;
import ru.example.mealapi.model.dto.mealdish.MealDishDto;
import ru.example.mealapi.model.dto.mealdish.NewMealDishDto;
import ru.example.mealapi.model.dto.user.NewUserDto;
import ru.example.mealapi.model.dto.user.UserDto;
import ru.example.mealapi.model.entity.Dish;
import ru.example.mealapi.model.entity.User;
import ru.example.mealapi.model.enums.ActivityType;
import ru.example.mealapi.model.enums.Targets;
import ru.example.mealapi.model.mapper.DishMapper;
import ru.example.mealapi.model.mapper.UserMapper;
import ru.example.mealapi.repo.DishRepository;
import ru.example.mealapi.repo.UserRepository;
import ru.example.mealapi.service.dish.DishService;
import ru.example.mealapi.service.meal.MealService;
import ru.example.mealapi.service.user.UserService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Transactional
@SpringBootTest(classes = MealApi.class)
@Rollback(value = false)
public class MealIntegrationTest {

    @Autowired
    private UserService userService;
    @Autowired
    private DishService dishService;
    @Autowired
    private MealService mealService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DishRepository dishRepository;

    private static NewUserDto newUser;
    private static NewDishDto newDish1, newDish2;

    private static UserDto user;
    private static DishDto dish1, dish2;
    private static List<MealDishDto> mealDishList;

    private static long userId, dishId1, dishId2;

    @BeforeEach
    void initData() {

        newUser = new NewUserDto(
                "John Doe",
                "test@example.com",
                35, 85,
                183,
                Targets.STABILIZE,
                ActivityType.NORMAL,
                "M");

        newDish1 = new NewDishDto("Dish 1", 100, 43.2, 33.5, 20.3);
        newDish2 = new NewDishDto("Dish 2", 200, 100.1, 30, 50);

        userId = userService.add(newUser).getId();
        dishId1 = dishService.add(newDish1).getId();
        dishId2 = dishService.add(newDish2).getId();

    }

    @Test
    void shouldCreateAndFindUserAndDishes() throws NotFoundCustomException {

        user = UserMapper.toDto(userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundCustomException(User.class.getSimpleName(), userId)));

        dish1 = DishMapper.toDto(dishRepository.findById(dishId1)
                .orElseThrow(() -> new NotFoundCustomException(Dish.class.getSimpleName(), dishId1)));

        dish2 = DishMapper.toDto(dishRepository.findById(dishId2)
                .orElseThrow(() -> new NotFoundCustomException(Dish.class.getSimpleName(), dishId2)));

        assertEquals(newUser.getName(), user.getName());
        assertEquals(newDish1.getName(), dish1.getName());
        assertEquals(newDish2.getName(), dish2.getName());

    }

    @Test
    void shouldAddNewMealAndDishes() throws NotFoundCustomException {

        List<NewMealDishDto> newMealDishDtoList = List.of(new NewMealDishDto(dishId1, 150.30),
                new NewMealDishDto(dishId2, 89.30));

        mealDishList = mealService.addDishes(1L, userId, newMealDishDtoList);

        UserDataReportDto userReport = mealService.getUsersReportByDay(LocalDate.now(), userId);

        assertEquals(328, userReport.getTotalKkal());
        assertEquals(1, userReport.getTotalMeals());
    }

    @Test
    void shouldReturnTargetIsUnreached() throws NotFoundCustomException {

        boolean res = mealService.checkUsersTarget(userId, LocalDate.now());

        assertFalse(res);

    }

}
