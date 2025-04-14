package com.example.demo.controller;


import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("cars")
@RestController
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }



    @GetMapping()
    public ResponseEntity<List<Car>> getCars() {

        return ResponseEntity.ok((List<Car>) carService.getCars());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable  Long id) {

        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-brand/{brand}")
    public ResponseEntity<List<Car>> getCarByBrand(@PathVariable String brand) {

        return ResponseEntity.ok(carService.getCarByBrand(brand));
    }

    @GetMapping("/by-model/{model}")
    public ResponseEntity<List<Car>> getCarByModel(@PathVariable String model) {
        return ResponseEntity.ok(carService.getCarByModel(model));
    }

    @PostMapping("/addCar")
    public ResponseEntity addCar(@Valid @RequestBody Car car)
    {
        Car savedCar = carService.addCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @PutMapping("/updateCar/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id,@Valid @RequestBody Car updatedCar) {
        try {
            Car car = carService.updateCar(id, updatedCar);
            return ResponseEntity.ok(car);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/deletecar/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

}
