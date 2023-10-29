package com.buycar.dto;

import com.buycar.entity.Fuel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
public class CarDto {
    @Min(1)
    private Long ownerId;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @Min(0)
    private Integer engineCapacity;

    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @Min(1900)
    private Integer year;

    @Min(0)
    private Integer price;
}
