package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("cars")
@RestController
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }



    @GetMapping()
    public Iterable<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("/id/{id}")
    public Optional<Car> getCarById(@PathVariable  Long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/by-brand/{brand}")
    public List<Car> getCarByBrand(@PathVariable String brand) {
        return carService.getCarByBrand(brand);
    }

    @GetMapping("/by-model/{model}")
    public List<Car> getCarByModel(@PathVariable String model) {
        return carService.getCarByModel(model);
    }

    @PostMapping("/addCar")
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @PutMapping("/updateCar/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return carService.updateCar(id, updatedCar);
    }

    @DeleteMapping("/deletecar/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

}
