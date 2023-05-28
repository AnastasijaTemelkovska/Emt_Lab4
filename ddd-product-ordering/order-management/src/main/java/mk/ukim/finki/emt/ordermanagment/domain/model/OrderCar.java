package mk.ukim.finki.emt.ordermanagment.domain.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.emt.ordermanagment.domain.valueobjects.CarId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "order_car")
@Getter
public class OrderCar extends AbstractEntity<OrderCarId> {

    private Money carPrice;
    @Column(name = "qty", nullable = false)
    private int quantity;
    @AttributeOverride(name="id", column = @Column(name = "car_id", nullable = false))
    private CarId carId;

    public OrderCar() {
        super(DomainObjectId.randomId(OrderCarId.class));
    }

    public Money subtotal() {
        return carPrice.multiply(quantity);
    }
    public OrderCar(@NonNull CarId carId, @NonNull Money carPrice, int qty) {
        super(DomainObjectId.randomId(OrderCarId.class));
        this.carId = carId;
        this.carPrice = carPrice;
        this.quantity = qty;
    }


}
