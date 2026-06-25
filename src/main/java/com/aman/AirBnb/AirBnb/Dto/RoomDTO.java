package com.aman.AirBnb.AirBnb.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class RoomDTO {
    private Long id;

    @NotBlank(message = "Room type is required")
    private String type;

    @NotNull(message = "Base price is required")
    @Min(value = 0, message = "Base price must be positive")
    private BigDecimal basePrice;

    private String[] photos;
    private String[] amenities;

    @NotNull(message = "Total count is required")
    @Min(value = 1, message = "Total count must be at least 1")
    private Integer totalCount;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;
}
