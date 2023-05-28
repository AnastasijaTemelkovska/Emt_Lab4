package mk.ukim.finki.emt.ordermanagment.domain.repository;

import mk.ukim.finki.emt.ordermanagment.domain.model.Order;
import mk.ukim.finki.emt.ordermanagment.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
