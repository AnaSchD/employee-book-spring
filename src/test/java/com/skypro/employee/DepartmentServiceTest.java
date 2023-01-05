package com.skypro.employee;


import com.skypro.employee.model.Employee;
import com.skypro.employee.repository.EmployeeRepository;
import com.skypro.employee.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private List<Employee> actualEmployee;

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Ivanov", "Ivan", 3, 2000);
        Employee employee2 = new Employee("Andreev", "Andrey", 2, 3000);
        Employee employee3 = new Employee("Petrov", "Petr", 1, 1000);

        actualEmployee = new ArrayList<>(List.of(employee1, employee2, employee3));
        Mockito.when(employeeRepository.getEmployees()).thenReturn(actualEmployee);
    }

    @Test //getAllEmployeeByDep
    public void checkingAllEmployeeByDepartment() {
        final Set<Integer> actual = actualEmployee.stream().map(Employee::getDEPARTMENT).collect(Collectors.toSet());
        final Set<Integer> expected = departmentService.getAllEmployeeByDep();
        assertEquals(actual, expected);
    }

    @Test // getEmployeesFromDepartment

    public void checkingEmployeesFromDepartment() {
        final int departmentId = 1;

        final List<Employee> actual = actualEmployee.stream().filter(e -> e.getDEPARTMENT() == departmentId).collect(Collectors.toList());
        final List<Employee> expected = departmentService.getEmployeesFromDepartment(departmentId);

        assertEquals(actual, expected);
    }

    @Test //getAllEmployeeByDepNumb
    public void checkingAllEmployeeByDepNumb() {
        final Map<Integer, List<Employee>> actual = actualEmployee.stream().map(Employee::getDEPARTMENT).collect(Collectors.toSet())
                .stream().collect(Collectors.toMap(dep -> dep, dep -> actualEmployee.stream().filter(e -> e.getDEPARTMENT() == dep)
                        .collect(Collectors.toList())));

        final Map<Integer, List<Employee>> expected = departmentService.getAllEmployeeByDepNumb();

        assertEquals(actual, expected);
    }

    @Test //getSalarySumByDep
    public void checkingSalarySumByDep() {
        final int departmentId = 1;

        final int actual = actualEmployee.stream().filter(e -> e.getDEPARTMENT() == departmentId).mapToInt(Employee::getSALARY).sum();
        final int expected = departmentService.getSalarySumByDep(departmentId);

        assertEquals(actual, expected);
    }

    @Test //getMaxSalaryByDep
    public void checkingMaxSalaryByDep() {
        final int departmentId = 1;

        final int actual = actualEmployee.stream().filter(e -> e.getDEPARTMENT() == departmentId).mapToInt(Employee::getSALARY).max().orElseThrow();
        final int expected = departmentService.getMaxSalaryByDep(departmentId);

        assertEquals(actual, expected);
    }

    @Test //getMinSalaryByDep
    public void checkingMinSalaryByDep() {
        final int departmentId = 1;

        final int actual = actualEmployee.stream().filter(e -> e.getDEPARTMENT() == departmentId).mapToInt(Employee::getSALARY).min().orElseThrow();
        final int expected = departmentService.getMinSalaryByDep(departmentId);

        assertEquals(actual, expected);
    }


}
