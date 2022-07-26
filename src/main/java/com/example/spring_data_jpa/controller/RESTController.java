package com.example.spring_data_jpa.controller;

import com.example.spring_data_jpa.entity.Employee;
import com.example.spring_data_jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        if (employeeService.getEmployee(id).isPresent()) {
            return employeeService.getEmployee(id).get();
        } else {
            throw new IllegalArgumentException("No such employee");
        }
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) { // тело запроса
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
       if (employeeService.getEmployee(id).isPresent()) {
           employeeService.removeEmployee(id);
       } else {
           throw new IndexOutOfBoundsException("No such employee exception");
       }
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) {
        return employeeService.findAllByName(name);
    }

}
