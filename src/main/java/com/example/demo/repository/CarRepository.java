package com.example.demo.repository;

import com.example.demo.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findCarByBrand(String brand);
    List<Car> findCarByModel(String model);
}
