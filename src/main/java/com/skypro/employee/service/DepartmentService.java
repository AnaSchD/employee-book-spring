package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeRepository employeeRepository;

    public DepartmentService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Set<Integer> getAllEmployeeByDep() {
        return employeeRepository.getEmployees().
                stream().map(Employee::getDEPARTMENT)
                .collect(Collectors.toSet());
    }

    public List<Employee> getEmployeesFromDepartment(int departmentId) {
        return employeeRepository.getEmployees().stream()
                .filter(employee -> employee.getDEPARTMENT() == departmentId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getAllEmployeeByDepNumb() {
        return getAllEmployeeByDep()
                .stream()
                .collect(Collectors.toMap(dep -> dep, this::getEmployeesFromDepartment));
    }

    public int getSalarySumByDep(int departmentId) {
        return getEmployeesFromDepartment(departmentId)
                .stream()
                .mapToInt(Employee::getSALARY)
                .sum();
    }

    public int getMaxSalaryByDep(int departmentId) {
        return getEmployeesFromDepartment(departmentId)
                .stream()
                .mapToInt(Employee::getSALARY)
                .max()
                .orElseThrow();
    }

    public int getMinSalaryByDep(int departmentId) {
        return getEmployeesFromDepartment(departmentId)
                .stream()
                .mapToInt(Employee::getSALARY)
                .min()
                .orElseThrow();
    }
}
