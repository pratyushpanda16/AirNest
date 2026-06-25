package com.aman.AirBnb.AirBnb.Dto;

import com.aman.AirBnb.AirBnb.Enums.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GuestDTO {
    private Long id;

    @NotBlank(message = "Guest name is required")
    private String name;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @Min(value = 0, message = "Age must be positive")
    private Integer age;
}
