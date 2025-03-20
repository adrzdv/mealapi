package ru.example.mealapi.service.meal;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.mealapi.exception.NotFoundCustomException;
import ru.example.mealapi.model.enums.Targets;
import ru.example.mealapi.model.dto.UserDataReportDto;
import ru.example.mealapi.model.dto.mealdish.MealDishDto;
import ru.example.mealapi.model.dto.mealdish.NewMealDishDto;
import ru.example.mealapi.model.entity.Dish;
import ru.example.mealapi.model.entity.Meal;
import ru.example.mealapi.model.entity.MealDish;
import ru.example.mealapi.model.entity.User;
import ru.example.mealapi.model.mapper.MealDishMapper;
import ru.example.mealapi.repo.DishRepository;
import ru.example.mealapi.repo.MealDishRepository;
import ru.example.mealapi.repo.MealRepository;
import ru.example.mealapi.repo.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MealServiceImp implements MealService {

    @Autowired
    MealDishRepository mealDishRepository;
    @Autowired
    MealRepository mealRepository;
    @Autowired
    DishRepository dishRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<MealDishDto> addDishes(long idMeal,
                                       long idUser,
                                       List<NewMealDishDto> dishes) throws NotFoundCustomException {

        List<Long> dishIds = dishes.stream()
                .map(NewMealDishDto::getIdDish)
                .toList();

        List<Dish> foundDishes = dishRepository.findAllById(dishIds);

        Set<Long> foundDishIds = foundDishes.stream()
                .map(Dish::getId)
                .collect(Collectors.toSet());

        List<Long> missingDishes = dishIds.stream()
                .filter(id -> !foundDishIds.contains(id))
                .toList();

        if (!missingDishes.isEmpty()) {
            throw new NotFoundCustomException(Dish.class.getSimpleName(), 0);
        }

        Meal meal = mealRepository.findById(idMeal)
                .orElseGet(() -> {
                    User user;
                    try {
                        user = userRepository.findById(idUser)
                                .orElseThrow(() -> new NotFoundCustomException(User.class.getSimpleName(), idUser));
                    } catch (NotFoundCustomException e) {
                        throw new RuntimeException(e);
                    }
                    return mealRepository.save(new Meal(null, user, LocalDate.now(), null));
                });

        List<MealDish> mealDishes = new ArrayList<>();

        for (NewMealDishDto dishDto : dishes) {

            Dish dish = null;

            for (Dish d : foundDishes) {
                if (d.getId().equals(dishDto.getIdDish())) {
                    dish = d;
                    break;
                }
            }
            if (dish == null) {
                throw new NotFoundCustomException(Dish.class.getSimpleName(), dishDto.getIdDish());
            }
            mealDishes.add(MealDishMapper.toEntity(dishDto, meal, dish));
        }

        List<MealDish> savedMealDishes = mealDishRepository.saveAll(mealDishes);

        return savedMealDishes.stream()
                .map(MealDishMapper::toDto)
                .toList();
    }

    @Override
    public UserDataReportDto getUsersReportByDay(LocalDate date, long idUser) {
        List<MealDish> mealDishList = mealDishRepository.getMealDishesInDate(date, idUser);
        UserDataReportDto reportObject = new UserDataReportDto();

        reportObject.setTotalKkal(countTotalKkal(mealDishList));

        int totalMeals = mealRepository.getUsersMealsInDay(idUser, date).size();
        reportObject.setTotalMeals(totalMeals);

        return reportObject;
    }

    @Override
    public boolean checkUsersTarget(long idUser, LocalDate date) throws NotFoundCustomException {

        List<MealDish> mealDishList = mealDishRepository.getMealDishesInDate(date, idUser);
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NotFoundCustomException(User.class.getSimpleName(), idUser));

        int totalKkal = countTotalKkal(mealDishList);
        int normalKkal = countNormalKkal(user);

        boolean isExceeded = totalKkal > normalKkal;
        boolean isUnderrated = totalKkal < normalKkal;

        if (user.getTarget().equals(Targets.STABILIZE.toString()) && !isExceeded && !isUnderrated) {
            return true;
        } else if (user.getTarget().equals(Targets.LOW_WEIGHT.toString()) && isUnderrated) {
            return true;
        } else return user.getTarget().equals(Targets.WEIGHT_INCREASE.toString()) && isExceeded;
    }

    @Override
    public List<MealDishDto> getUsersDailyMeals(long idUser, LocalDate date) {

        return mealDishRepository.getMealDishesInDate(date, idUser).stream()
                .map(MealDishMapper::toDto)
                .toList();
    }

    /**
     * Расчет дневной нормы калорий по формуле Миффлина-Сан Жеора
     *
     * @param user пользователь, для которого производится расчет
     * @return значение дневной нормы калорий для пользователя
     */
    private int countNormalKkal(User user) {
        double normal;
        double kAct;

        switch (user.getActivityType()) {
            case "NORMAL" -> kAct = 1.2;
            case "LIGHT_ACTIVE" -> kAct = 1.375;
            case "MIDDLE_ACTIVE" -> kAct = 1.55;
            case "HIGH_ACTIVE" -> kAct = 1.725;
            case "INTENSE_ACTIVE" -> kAct = 1.9;
            default -> kAct = 0;
        }

        double bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge();

        if (user.getGender().equals("M")) {
            normal = kAct * (bmr + 5);
        } else {
            normal = kAct * (bmr - 161);
        }

        return (int) normal;
    }

    /**
     * Расчет суммы калорий пользователя за день
     *
     * @param mealDishList список приемов пищи за день
     * @return сумму калорий, потребленных пользователем
     */
    private int countTotalKkal(List<MealDish> mealDishList) {
        double totalKkal = 0.0;

        for (MealDish mealDish : mealDishList) {
            double dishKkal = mealDish.getPortionSize() * mealDish.getDish().getKkal() / 100;
            totalKkal += dishKkal;
        }

        return (int) totalKkal;
    }

}
