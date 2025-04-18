package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Iterable<Car> getCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarByBrand(String brand) {
        return carRepository.findCarByBrand(brand);
    }

    public List<Car> getCarByModel(String model) {
        return carRepository.findCarByModel(model);
    }

    public Car addCar(Car car) {
       return carRepository.save(car);
    }

    public Car updateCar(Long id, Car updatedCar) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setBrand(updatedCar.getBrand());
                    car.setModel(updatedCar.getModel());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

}
