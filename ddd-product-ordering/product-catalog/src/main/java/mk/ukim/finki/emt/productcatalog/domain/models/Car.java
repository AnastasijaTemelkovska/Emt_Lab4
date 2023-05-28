package mk.ukim.finki.emt.productcatalog.domain.models;

import jakarta.persistence.*;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "car")
public class Car extends AbstractEntity<CarId> {
    private int sales = 0;
    private String carMake;
    private String carModel;
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money price;
    public Car() {
        super(CarId.randomId(CarId.class));
    }

    public static Car build(String carMake, String carModel, Category category, Money price, int sales) {
        Car c = new Car();
        c.price = price;
        c.carMake = carMake;
        c.carModel = carModel;
        c.sales = sales;
        return c;
    }

    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }
}
