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
    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    public class CarServiceTest {

        @Mock
        CarRepository carRepository;
        @InjectMocks
        CarService carService;
        private List<Car> carList;

        @BeforeEach
        void init() {
            carList = List.of(
                    new Car() {{
                        setId(1L);
                        setBrand("Toyota");
                        setModel("Supra");
                    }},
                    new Car() {{
                        setId(2L);
                        setBrand("BMW");
                        setModel("M3");
                    }}
            );
        }

        @Test
        void addCarShouldAddCarSuccessfully() {
            Car car = carList.get(0);
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

        @Test
        void getCarsShouldGetAllCarsSuccessfully() {
            when(carRepository.findAll()).thenReturn(carList);

            Iterable<Car> cars = carService.getCars();

            assertNotNull(cars);
            assertIterableEquals(carList, cars);
        }

        @Test
        void getCarByIdShouldGetCarById() {
            when(carRepository.findById(1L)).thenReturn(Optional.of(carList.get(1)));

            Optional<Car> car = carService.getCarById(1L);

            assertTrue(car.isPresent());
            assertEquals(carList.get(1), car.get());

        }


    }
