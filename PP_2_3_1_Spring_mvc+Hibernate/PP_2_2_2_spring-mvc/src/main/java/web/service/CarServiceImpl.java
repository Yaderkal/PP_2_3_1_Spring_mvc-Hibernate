package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private List<Car> cars;

    {
        cars = new ArrayList<>();
        Car car = new Car("Geely", "Mondjaro", 2023);
        Car car1 = new Car("Cherry", "Tiggo", 2020);
        Car car2 = new Car("Toyota", "Camry", 2015);
        Car car3 = new Car("Hyndai", "Creta", 2020);
        Car car4 = new Car("Ford", "Escape", 2011);
        cars.add(car);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
    }

    @Override
    public List<Car> index() {
        return cars;
    }

    @Override
    public List<Car> count(Integer count) {
        if (count == null || count >= cars.size() || count <= 0) {
            return index();
        }
        return new ArrayList<Car>(cars.subList(0, count));
    }
}
