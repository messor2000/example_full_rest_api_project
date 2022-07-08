package crud.example.restapiproject.service.employee;

import crud.example.restapiproject.controller.assembler.EmployeeModelAssembler;
import crud.example.restapiproject.entity.Employee;
import crud.example.restapiproject.exception.EmployeeNotFoundException;
import crud.example.restapiproject.repo.EmployeeRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    public final EmployeeModelAssembler assembler;

    AppEmployeeService(EmployeeRepository employeeRepository, EmployeeModelAssembler assembler) {
        this.employeeRepository = employeeRepository;
        this.assembler = assembler;
    }

    @Override
    public List<EntityModel<Employee>> findAll() {
        return employeeRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public EntityModel<Employee> save(Employee employee) {
        return assembler.toModel(employeeRepository.save(employee));
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee newEmployee, Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }
}
