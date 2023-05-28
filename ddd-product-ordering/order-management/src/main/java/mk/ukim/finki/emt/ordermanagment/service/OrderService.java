package mk.ukim.finki.emt.ordermanagment.service;

import mk.ukim.finki.emt.ordermanagment.domain.exceptions.OrderCarIdNotExistException;
import mk.ukim.finki.emt.ordermanagment.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagment.domain.model.Order;
import mk.ukim.finki.emt.ordermanagment.domain.model.OrderCar;
import mk.ukim.finki.emt.ordermanagment.domain.model.OrderCarId;
import mk.ukim.finki.emt.ordermanagment.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagment.service.forms.OrderCarForm;
import mk.ukim.finki.emt.ordermanagment.service.forms.OrderForm;


import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderId placeOrder(OrderForm orderForm);

    List<Order> findAll();

    Optional<Order> findById(OrderId id);

    void addCar(OrderId orderId, OrderCarForm orderCarForm) throws OrderIdNotExistException;

    void deleteCar(OrderId orderId, OrderCarId orderCarId) throws OrderIdNotExistException, OrderCarIdNotExistException;


}
