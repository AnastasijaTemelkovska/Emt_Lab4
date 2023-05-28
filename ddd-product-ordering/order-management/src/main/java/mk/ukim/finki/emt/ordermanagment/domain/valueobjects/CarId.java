package mk.ukim.finki.emt.ordermanagment.domain.valueobjects;

import jakarta.persistence.Embeddable;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class CarId extends DomainObjectId {
    public CarId() {
        super(CarId.randomId(CarId.class).getId());
    }

    public CarId(String uuid) {
        super(uuid);
    }

}
