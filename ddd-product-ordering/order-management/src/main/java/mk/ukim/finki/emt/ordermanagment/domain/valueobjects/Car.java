package mk.ukim.finki.emt.ordermanagment.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Car implements ValueObject {
    private final CarId id;
    private final String name;
    private final Money price;


    private Car() {
        this.id=CarId.randomId(CarId.class);
        this.name= "";
        this.price = Money.valueOf(Currency.MKD,0);

    }


    }

