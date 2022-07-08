package crud.example.restapiproject.service.order;

import crud.example.restapiproject.entity.order.Order;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order findById(Long id);

    List<EntityModel<Order>> findAll();
}
