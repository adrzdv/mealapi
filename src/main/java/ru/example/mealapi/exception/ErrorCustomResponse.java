package ru.example.mealapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorCustomResponse {
    private String header;
    private String description;
}
