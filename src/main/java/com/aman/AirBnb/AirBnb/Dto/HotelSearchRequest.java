package com.aman.AirBnb.AirBnb.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchRequest {
    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Rooms count is required")
    @Min(value = 1, message = "At least 1 room is required")
    private Integer roomsCount;

    private Integer page = 0;
    private Integer size = 10;
}