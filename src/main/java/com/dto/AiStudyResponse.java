package com.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AiStudyResponse(@NotBlank String title,
                              @NotBlank String description,
                              @NotBlank String example,
                              @Min(1) @Max(5) int difficulty) {

}
