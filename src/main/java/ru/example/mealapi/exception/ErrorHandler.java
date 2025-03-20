package ru.example.mealapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorCustomResponse notFoundCustomException(final NotFoundCustomException e) {
        return new ErrorCustomResponse("NOT_FOUND", e.getMessage());
    }
}
