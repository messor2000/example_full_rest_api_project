package crud.example.restapiproject.util;

import crud.example.restapiproject.entity.Employee;
import crud.example.restapiproject.entity.order.Order;
import crud.example.restapiproject.entity.order.Status;
import crud.example.restapiproject.repo.EmployeeRepository;
import crud.example.restapiproject.repo.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        return args -> {
            employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
            employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));


            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
        };
    }
}
