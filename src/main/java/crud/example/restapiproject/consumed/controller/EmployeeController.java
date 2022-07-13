package crud.example.restapiproject.consumed.controller;

import crud.example.restapiproject.entity.Employee;
import crud.example.restapiproject.exception.EmployeeNotFoundException;
import crud.example.restapiproject.service.employee.EmployeeService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class EmployeeController {

    private final String URI_EMPLOYEES = "/employee";
    private final String URI_EMPLOYEES_ID = "/employee/{id}";

    private final RestTemplate restTemplate;
    private final EmployeeService employeeService;

    EmployeeController(RestTemplate restTemplate, EmployeeService employeeService) {
        this.restTemplate = restTemplate;
        this.employeeService = employeeService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Employee>> getAll() {
        Employee[] employees = restTemplate.getForObject(URI_EMPLOYEES, Employee[].class);
        return new ResponseEntity<>(Arrays.asList(Objects.requireNonNull(employees)), HttpStatus.OK);
    }

    @GetMapping("all/{id}")
    public ResponseEntity<Employee> getById(@PathVariable long id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");

        Employee employee = restTemplate.getForObject(URI_EMPLOYEES_ID, Employee.class, params);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("employees")
    public Employee create(@RequestBody final Employee employee) {
        return restTemplate.postForObject(URI_EMPLOYEES, employee, Employee.class);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<Employee> update(@RequestBody final Employee employee) {
        EntityModel<Employee> updatedEmployee = employeeService.save(employee);

        return new ResponseEntity<>(updatedEmployee.getContent(), HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public HttpStatus delete(@PathVariable long id) {
        try {
            employeeService.deleteById(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new EmployeeNotFoundException(id);
        }
    }
}
