package ru.example.mealapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Аннотация для валидации поля на соответствие допустимым значениям пола ('M' или 'F').
 * <p>
 * Пример использования:
 * <pre>
 * &#64;ValidGender
 * private String gender;
 * </pre>
 */
@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGender {
    String message() default "Gender must be 'M' or 'F'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
