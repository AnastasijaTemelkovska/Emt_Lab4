package mk.ukim.finki.emt.sharedkernel.domain.base;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.Objects;


@MappedSuperclass
@Getter
public class AbstractEntity<ID extends DomainObjectId > {
    @EmbeddedId
    private ID id;
    protected AbstractEntity() {
    }


    protected AbstractEntity(@NonNull ID id) {
        this.id = Objects.requireNonNull(id, "id must not be null");
    }
}
