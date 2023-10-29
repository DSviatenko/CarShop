package com.buycar.service;

import com.buycar.entity.Car;
import com.buycar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.buycar.util.validation.ValidationUtil.checkNotFound;
import static com.buycar.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return checkNotFoundWithId(carRepository.findById(id), id);
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Car updatedCar, Long id) {
        Car car = checkNotFoundWithId(carRepository.findById(id), id);

        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setEngineCapacity(updatedCar.getEngineCapacity());
        car.setFuel(updatedCar.getFuel());
        car.setYear(updatedCar.getYear());
        car.setPrice(updatedCar.getPrice());

        carRepository.save(car);

        return checkNotFoundWithId(carRepository.findById(id), id);
    }

    public void deleteCar(Long id) {
        checkNotFoundWithId(carRepository.findById(id), id);
        carRepository.deleteById(id);
    }
}
