package ru.example.model.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.model.Targets;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewUserDto {
    @NotBlank(message = "Error: must not be blank. Value: ${validatedValue}")
    @Size(message = "Error: length must be between 1 and 254 characters. Value: ${validatedValue}", min = 1, max = 254)
    private String name;

    @NotBlank(message = "Error: must not be blank. Value: ${validatedValue}")
    @Email(message = "Email has invalid format. Value: ${validatedValue}",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @Size(message = "Error: length must be between 2 and 320 characters. Value: ${validatedValue}", min = 2, max = 320)
    private String email;

    @Positive(message = "Error: value must be positive")
    @Max(value = 100, message = "Error: age cannot be more than 100")
    private int age;

    @Positive(message = "Error: value must be positive")
    @Max(value = 500, message = "Error: weight cannot be more than 500 kg")
    private int weight;

    @Positive(message = "Error: value must be positive")
    @Max(value = 300, message = "Error: height cannot be more than 300 cm")
    private int height;

    @NotNull(message = "Error: must not be empty. Value: ${validatedValue}")
    private Targets target;
}
