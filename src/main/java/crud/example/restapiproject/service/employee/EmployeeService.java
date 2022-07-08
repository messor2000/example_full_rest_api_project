package crud.example.restapiproject.service.employee;

import crud.example.restapiproject.entity.Employee;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface EmployeeService {
    List<EntityModel<Employee>> findAll();

    EntityModel<Employee> save(Employee employee);

    Employee findById(Long id);

    void deleteById(Long id);

    Employee updateEmployee(Employee employee, Long id);
}
