package mk.ukim.finki.emt.ordermanagment.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagment.domain.exceptions.OrderCarIdNotExistException;
import mk.ukim.finki.emt.ordermanagment.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagment.domain.model.Order;
import mk.ukim.finki.emt.ordermanagment.domain.model.OrderCarId;
import mk.ukim.finki.emt.ordermanagment.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagment.domain.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagment.service.OrderService;
import mk.ukim.finki.emt.ordermanagment.service.forms.OrderCarForm;
import mk.ukim.finki.emt.ordermanagment.service.forms.OrderForm;
import org.hibernate.validator.internal.engine.validationcontext.ValidatorScopedContext;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

   private final OrderRepository orderRepository;
   private final Validator validator;
    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        //newOrder.getOrderItemList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getProductId().getId(),item.getQuantity())));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addCar(OrderId orderId, OrderCarForm orderCarForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(orderCarForm.getCar(),orderCarForm.getQuantity());
        orderRepository.saveAndFlush(order);
   //     domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getProduct().getId().getId(),orderItemForm.getQuantity()));

    }

    @Override
    public void deleteCar(OrderId orderId, OrderCarId orderCarId) throws OrderIdNotExistException, OrderCarIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeCar(orderCarId);
        orderRepository.saveAndFlush(order);
    }
    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now());
        orderForm.getItems().forEach(item->order.addItem(item.getCar(),item.getQuantity()));
        return order;
    }
}
