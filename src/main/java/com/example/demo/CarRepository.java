package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findCarByBrand(String brand);
    List<Car> findCarByModel(String model);
}
