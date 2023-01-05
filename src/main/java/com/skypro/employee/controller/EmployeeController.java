package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import com.skypro.employee.exception.EmployeeValidationException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController

public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployee();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) throws EmployeeValidationException {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public int getMinSalary() {
        return this.employeeService.getMinSalary();
    }

    @GetMapping("/employees/salary/max")
    public int getMaxSalary() {
        return this.employeeService.getMaxSalary();
    }

    @GetMapping("/employees/highSalary")
    public Employee getHighSalary(EmployeeRequest employeeRequest) {
        return this.employeeService.getHighSalary(employeeRequest);
    }
}
