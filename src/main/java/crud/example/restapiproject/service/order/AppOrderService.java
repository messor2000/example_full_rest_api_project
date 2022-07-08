package crud.example.restapiproject.service.order;

import crud.example.restapiproject.controller.assembler.OrderModelAssembler;
import crud.example.restapiproject.entity.order.Order;
import crud.example.restapiproject.entity.order.Status;
import crud.example.restapiproject.exception.OrderNotFoundException;
import crud.example.restapiproject.repo.OrderRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler assembler;

    AppOrderService(OrderRepository orderRepository, OrderModelAssembler assembler) {
        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @Override
    public Order save(Order order) {
        order.setStatus(Status.IN_PROGRESS);
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<EntityModel<Order>> findAll() {
        return orderRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }
}
