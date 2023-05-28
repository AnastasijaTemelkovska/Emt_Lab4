package mk.ukim.finki.emt.productcatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.exceptions.CarNotFoundException;
import mk.ukim.finki.emt.productcatalog.domain.models.Car;
import mk.ukim.finki.emt.productcatalog.domain.models.CarId;
import mk.ukim.finki.emt.productcatalog.domain.repository.CarRepository;
import mk.ukim.finki.emt.productcatalog.services.CarService;
import mk.ukim.finki.emt.productcatalog.services.form.CarForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CarServiceImpl implements CarService {
   private final CarRepository carRepository;

    @Override
    public Car findById(CarId id) {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    @Override
    public Car createProduct(CarForm form) {
        Car c = Car.build(form.getProductMake(), form.getProductModel(),form.getCategory(), form.getPrice(), form.getSales());
        carRepository.save(c);
        return c;
    }

    @Override
    public Car orderItemCreated(CarId carId, int quantity) {
        Car c = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        c.addSales(quantity);
        carRepository.saveAndFlush(c);
        return c;
    }

    @Override
    public Car orderItemRemoved(CarId carId, int quantity) {
        Car c = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        c.removeSales(quantity);
        carRepository.saveAndFlush(c);
        return c;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }
}
