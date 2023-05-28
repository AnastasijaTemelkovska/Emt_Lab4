package mk.ukim.finki.emt.ordermanagment.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagment.domain.valueobjects.Car;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;



@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderedOn;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderCar> orderCarList;


    public Order(Instant now) {
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = now;
        this.currency = currency;
    }

    public Order() {

    }

    public Money total() {
        return orderCarList.stream().map(OrderCar::subtotal).reduce(new Money(currency, 0),Money::add);
    }

    public OrderCar addItem(@NonNull Car car, int qty) {
        Objects.requireNonNull(car,"car must not be null");
        var item  = new OrderCar(car.getId(),car.getPrice(),qty);
        orderCarList.add(item);
        return item;
    }

    public void removeCar(@NonNull OrderCarId orderCarId) {
        Objects.requireNonNull(orderCarId,"Order Item must not be null");
        orderCarList.removeIf(v->v.getId().equals(orderCarId));
    }
}


