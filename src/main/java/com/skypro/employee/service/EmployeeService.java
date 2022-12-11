package com.skypro.employee.service;

import com.skypro.employee.exception.EmployeeValidationException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
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

    public Employee addEmployee(EmployeeRequest employeeRequest) throws EmployeeValidationException {
        checkEmployee(employeeRequest);
        Employee employee = new Employee(
                employeeRequest.setFirstName(StringUtils.capitalize(employeeRequest.getFirstName())),
                employeeRequest.setLastName(StringUtils.capitalize(employeeRequest.getLastName())),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getID(), employee);
        return employee;

//                employeeRequest.getFirstName(),
//                employeeRequest.getLastName(),
//                employeeRequest.getDepartment(),
//                employeeRequest.getSalary());
//        if (employeeRequest.getLastName() == null || employeeRequest.getFirstName() == null) {
//                throw new IllegalArgumentException("Установите ФИО сотрудника");

    }


    public int getSalarySum() {
        return employees.values().stream().mapToInt(e -> e.getSALARY()).sum();
    }

    public int getAverageSalary() {
        return (int) employees.values().stream().mapToInt(Employee::getSALARY).average().orElseThrow();
    }

    public int getMinSalary() {
        return employees.values().stream().mapToInt(Employee::getSALARY).min().orElseThrow();
    }

    public int getMaxSalary() {
        return employees.values().stream().mapToInt(Employee::getSALARY).max().orElseThrow();
    }

    public Employee getHighSalary(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName(),
                employeeRequest.getDepartment(), employeeRequest.getSalary());
        if (employeeRequest.getSalary() > getAverageSalary()) {
            this.employees.put(employee.getSALARY(), employee);
        }
        return employee;
    }

    public void checkEmployee(EmployeeRequest employeeRequest) throws EmployeeValidationException {
        boolean firstName = StringUtils.isEmpty(employeeRequest.getFirstName());
        boolean lastName = StringUtils.isEmpty(employeeRequest.getLastName());
        boolean firstNameFirstSymbol = StringUtils.isAlpha(employeeRequest.getFirstName());
        boolean lastNameFirstSymbol = StringUtils.isAlpha(employeeRequest.getLastName());
        if (firstName || lastName || !firstNameFirstSymbol || !lastNameFirstSymbol) {
            throw new EmployeeValidationException("Ошибка валидации, некорректные ФИО");
        }
    }


}
