package ru.example.mealapi.exception;

public class NotFoundCustomException extends Exception{
    public NotFoundCustomException(String objectType, long id) {
        super(objectType + " с id = " + id + " не найден");
    }
}
