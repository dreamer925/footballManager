package com.dreamer925.footballManager.forms;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record PlayerForm(@NotBlank String name, @NotBlank String surname,
                         @Min(value = 16, message = "Age should not be less than 18")
                         @Max(value = 100, message = "Age should not be greater than 100")
                         int age, @PositiveOrZero int monthsOfExperience, @NotBlank String teamName) {
}
