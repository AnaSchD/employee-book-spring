package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service

public class EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployee() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getLastName() == null || employeeRequest.getFirstName() == null) {
            throw new IllegalArgumentException("Установите ФИО сотрудника");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getID(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream().mapToInt(e -> e.getSALARY()).sum();
    }


}
