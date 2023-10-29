package com.buycar.controller;

import com.buycar.dto.CarDto;
import com.buycar.entity.Car;
import com.buycar.service.CarService;
import com.buycar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable("id") Long id) {
        return carService.getCar(id);
    }

    @PostMapping
    public Car createCar(@Valid @RequestBody CarDto carDto) {
        Car car = new Car(userService.getUser(carDto.getOwnerId()), carDto.getBrand(), carDto.getModel(), carDto.getEngineCapacity(), carDto.getFuel(), carDto.getYear(), carDto.getPrice());

        return carService.createCar(car);
    }

    @PostMapping("/{id}")
    public Car updateCar(@Valid @RequestBody Car car, @PathVariable Long id) {
        return carService.updateCar(car, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
