package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addCar(@RequestBody Car car)
    {
        carService.addCar(car);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
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
