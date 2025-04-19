package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    CarRepository carRepository;
    @InjectMocks
    CarService carService;
    private static Car car = null;

    @BeforeEach
    void init() {
        car = new Car();
        car.setId(1L);
        car.setBrand("Toyota");
        car.setModel("Supra");
    }

    @Test
    void addCarShouldAddCarSuccessfully() {
        when(carRepository.save(car)).thenReturn(car);

        Car addedCar = carService.addCar(car);
        assertNotNull(addedCar);
        assertEquals(car.getId(), addedCar.getId());
        assertEquals(car.getBrand(), addedCar.getBrand());
        assertEquals(car.getModel(), addedCar.getModel());
    }
    @Test
    void deleteCarShouldDeleteCarSuccessfully() {
        doNothing().when(carRepository).deleteById(1L);
        carService.deleteCar(1L);
        verify(carRepository, times(1)).deleteById(1L);
    }

}
