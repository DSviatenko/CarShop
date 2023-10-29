package com.buycar.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Entity
@Table(name = "car")
@Validated
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User owner;

    @NotBlank(message = "Brand cannot be blank")
    @Column(name = "brand")
    private String brand;

    @NotBlank(message = "Model cannot be blank")
    @Column(name = "model")
    private String model;

    @PositiveOrZero
    @Column(name = "engine_capacity")
    private Integer engineCapacity;

    @Column(name = "fuel")
    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @Min(1900)
    @Column(name = "year_of_production")
    private Integer year;

    @PositiveOrZero
    @NotNull(message = "Price cannot be null")
    @Column(name = "price")
    private Integer price;

    public Car(User owner, /*Long ownerId,*/ String brand, String model, Integer engineCapacity, Fuel fuel, Integer year, Integer price) {
        this.owner = owner;
//        this.ownerId = ownerId;
        this.brand = brand;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.fuel = fuel;
        this.year = year;
        this.price = price;
    }
}
