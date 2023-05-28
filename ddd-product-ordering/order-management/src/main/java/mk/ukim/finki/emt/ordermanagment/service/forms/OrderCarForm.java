package mk.ukim.finki.emt.ordermanagment.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.ordermanagment.domain.valueobjects.Car;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Data
public class OrderCarForm {
    @NotNull
    private Car car;

    @Min(1)
    private int quantity = 1;
}
