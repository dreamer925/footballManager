package com.dreamer925.footballManager.forms;

import com.dreamer925.footballManager.models.PlayerModel;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

public record TeamForm(@NotBlank String name, @DecimalMin("0") @DecimalMax("10") BigDecimal fee,
                       @PositiveOrZero BigDecimal account, List<PlayerModel> players) {}
