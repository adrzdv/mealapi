package ru.example.mealapi.service.meal;

import ru.example.mealapi.exception.NotFoundCustomException;
import ru.example.mealapi.model.dto.UserDataReportDto;
import ru.example.mealapi.model.dto.mealdish.MealDishDto;
import ru.example.mealapi.model.dto.mealdish.NewMealDishDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для управления приёмами пищи пользователей.
 * Определяет методы для добавления блюд, получения отчетов и проверки целей.
 */
public interface MealService {

    /**
     * Добавляет блюда в приём пищи пользователя.
     *
     * @param idMeal идентификатор приёма пищи
     * @param idUser идентификатор пользователя
     * @param dishes список блюд
     * @return список блюд в данном приеме пиши
     * @throws NotFoundCustomException в случае, если не найден пользователь или какое-то из блюд
     */
    List<MealDishDto> addDishes(long idMeal, long idUser, List<NewMealDishDto> dishes) throws NotFoundCustomException;

    /**
     * Получает отчёт пользователя за определённый день.
     *
     * @param date   дата отчёта
     * @param idUser идентификатор пользователя
     * @return объект отчёта с данными пользователя за день
     */
    UserDataReportDto getUsersReportByDay(LocalDate date, long idUser);

    /**
     * Проверяет достижение пользователем дневной нормы по питанию.
     *
     * @param idUser    идентификатор пользователя
     * @param localDate дата, за которою производится проверка
     * @return true, если значение не превышено, иначе false
     * @throws NotFoundCustomException если пользователь не найден
     */
    boolean checkUsersTarget(long idUser, LocalDate localDate) throws NotFoundCustomException;

    /**
     * Получает список блюд пользователя за определённый день.
     *
     * @param idUser идентификатор пользователя
     * @param date   дата для получения списка блюд
     * @return список блюд пользователя за указанный день
     */
    List<MealDishDto> getUsersDailyMeals(long idUser, LocalDate date);


}
