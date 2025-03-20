package ru.example.mealapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.mealapi.model.dto.UserDataReportDto;
import ru.example.mealapi.model.dto.mealdish.MealDishDto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.example.mealapi.exception.NotFoundCustomException;
import ru.example.mealapi.model.dto.mealdish.NewMealDishDto;
import ru.example.mealapi.model.entity.Dish;
import ru.example.mealapi.model.entity.Meal;
import ru.example.mealapi.model.entity.MealDish;
import ru.example.mealapi.model.entity.User;
import ru.example.mealapi.model.enums.ActivityType;
import ru.example.mealapi.model.enums.Targets;
import ru.example.mealapi.repo.DishRepository;
import ru.example.mealapi.repo.MealDishRepository;
import ru.example.mealapi.repo.MealRepository;
import ru.example.mealapi.repo.UserRepository;
import ru.example.mealapi.service.meal.MealServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealServiceImpTest {

    @InjectMocks
    private MealServiceImp mealService;

    @Mock
    private MealDishRepository mealDishRepository;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldAddNewDishesWhenAllDishesExist() throws NotFoundCustomException {
        long mealId = 1L;
        long userId = 1L;
        List<NewMealDishDto> dishes = new ArrayList<>();
        dishes.add(new NewMealDishDto(1L, 200));
        dishes.add(new NewMealDishDto(2L, 150));

        Dish dish1 = new Dish(1L, "Dish 1", 100, 43.2, 33.5, 20.3);
        Dish dish2 = new Dish(2L, "Dish 2", 200, 100.1, 30, 50);
        List<Dish> foundDishes = List.of(dish1, dish2);

        when(dishRepository.findAllById(List.of(1L, 2L))).thenReturn(foundDishes);
        when(mealRepository.findById(mealId)).thenReturn(Optional.of(new Meal()));
        when(mealDishRepository.saveAll(anyList())).thenReturn(new ArrayList<>());

        List<MealDishDto> result = mealService.addDishes(mealId, userId, dishes);

        assertNotNull(result);
        verify(mealDishRepository, times(1)).saveAll(anyList());
    }

    @Test
    void shouldThrowExceptionWhenSomeDishesNotFound() {
        long mealId = 1L;
        long userId = 1L;
        List<NewMealDishDto> dishes = List.of(new NewMealDishDto(1L, 200), new NewMealDishDto(3L, 150));

        when(dishRepository.findAllById(List.of(1L, 3L)))
                .thenReturn(List.of(new Dish(1L, "Dish 1", 100, 43.2, 33.5, 20.3)));

        assertThrows(NotFoundCustomException.class,
                () -> mealService.addDishes(mealId, userId, dishes));
    }

    @Test
    void shouldCreateMealWhenMealNotFound() throws NotFoundCustomException {
        long mealId = 1L;
        long userId = 1L;
        List<NewMealDishDto> dishes = List.of(new NewMealDishDto(1L, 200));

        Dish dish1 = new Dish(1L, "Dish 1", 100, 43.2, 33.5, 20.3);
        when(dishRepository.findAllById(List.of(1L))).thenReturn(List.of(dish1));
        when(mealRepository.findById(mealId)).thenReturn(Optional.empty());
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(mealRepository.save(any(Meal.class))).thenReturn(new Meal());

        List<MealDishDto> result = mealService.addDishes(mealId, userId, dishes);

        assertNotNull(result);
        verify(mealRepository, times(1)).save(any(Meal.class));
    }

    @BeforeEach
    void initData() {

    }

    @Test
    void shouldCountTotalKkalAndMeals() {

        Dish dish1 = new Dish(1L, "Dish 1", 100, 43.2, 33.5, 20.3);
        User user = new User(1L,
                "John Doe",
                "test@example.com",
                35, 85,
                183,
                Targets.STABILIZE.toString(),
                ActivityType.NORMAL.toString(),
                "M");
        List<MealDish> mealDishList = List.of(new MealDish(1L, new Meal(), dish1, 100.00));
        Meal meal = new Meal(1L, user, LocalDate.now(), mealDishList);
        List<Meal> mealList = List.of(meal);


        when(mealDishRepository.getMealDishesInDate(any(), anyLong())).thenReturn(mealDishList);
        when(mealRepository.getUsersMealsInDay(anyLong(), any())).thenReturn(mealList);

        UserDataReportDto reportDto = mealService.getUsersReportByDay(LocalDate.now(), 1L);

        assertEquals(100, reportDto.getTotalKkal());
        assertEquals(1, reportDto.getTotalMeals());

    }

    @Test
    void shouldCheckUsersTargetFalse() throws NotFoundCustomException {

        User user1 = new User(1L,
                "John Doe",
                "test@example.com",
                35, 85,
                183,
                Targets.STABILIZE.toString(),
                ActivityType.NORMAL.toString(),
                "M");
        Dish dish1 = new Dish(1L, "Dish 1", 100, 43.2, 33.5, 20.3);
        List<MealDish> mealDishList1 = List.of(new MealDish(1L, new Meal(), dish1, 100.00));


        when(mealDishRepository.getMealDishesInDate(any(), anyLong())).thenReturn(mealDishList1);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        boolean checkData = mealService.checkUsersTarget(user1.getId(), LocalDate.now());

        assertFalse(checkData);
    }

    @Test
    void shouldCheckUsersTargetTrue() throws NotFoundCustomException {

        User user2 = new User(2L,
                "Doe John",
                "test2@example.com",
                30, 65,
                160,
                Targets.WEIGHT_INCREASE.toString(),
                ActivityType.MIDDLE_ACTIVE.toString(),
                "F");
        Dish dish2 = new Dish(2L, "Dish 2", 100, 43.2, 33.5, 20.3);
        List<MealDish> mealDishList2 = List.of(new MealDish(1L, new Meal(), dish2, 2500));

        when(mealDishRepository.getMealDishesInDate(any(), anyLong())).thenReturn(mealDishList2);
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        boolean checkData1 = mealService.checkUsersTarget(user2.getId(), LocalDate.now());

        assertTrue(checkData1);
    }
}