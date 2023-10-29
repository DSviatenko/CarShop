package com.buycar.controller.util;

import com.buycar.entity.Fuel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CarInfoTest {
    public Long id;
    public Long owner;
    public String brand;
    public String model;
    public Integer engineCapacity;
    public Fuel fuel;
    public Integer year;
    public Integer price;
}
