package mk.ukim.finki.emt.productcatalog.services;

import mk.ukim.finki.emt.productcatalog.domain.models.Car;
import mk.ukim.finki.emt.productcatalog.domain.models.CarId;
import mk.ukim.finki.emt.productcatalog.services.form.CarForm;

import java.util.List;

public interface CarService {
    Car findById(CarId id);
    Car createProduct(CarForm form);
    Car orderItemCreated(CarId carId, int quantity);
    Car orderItemRemoved(CarId carId, int quantity);
    List<Car> getAll();
}
