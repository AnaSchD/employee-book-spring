package com.skypro.employee;

import com.skypro.employee.exception.EmployeeValidationException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.repository.EmployeeRepository;
import com.skypro.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private List<Employee> actualEmployee;

    private Employee employee1;

    @BeforeEach
    public void setUp() {
        employee1 = new Employee("Ivanov", "Ivan", 2, 20000);
        Employee employee2 = new Employee("Ivanov2", "Ivan2", 1, 30000);
        Employee employee3 = new Employee("Ivanov3", "Iva3", 3, 40000);

        actualEmployee = new ArrayList<>(List.of(employee1, employee2, employee3));
        Mockito.when(employeeRepository.getEmployees()).thenReturn(actualEmployee);
    }

    @Test //getAllEmployee
    public void checkingAllEmployees() {
        Collection <Employee> expected = employeeService.getAllEmployee();
        assertEquals(actualEmployee, expected);
    }

    @Test //addEmployee
    public void checkingAddEmployeeWithException() {
        EmployeeRequest badEmp = new EmployeeRequest();
        badEmp.setLastName("123");
        badEmp.setFirstName("123");
        badEmp.setDepartment(1);
        badEmp.setSalary(4000);

        Mockito.when(employeeRepository.getLastId()).thenReturn(1);
        assertThrows(EmployeeValidationException.class, () -> employeeService.addEmployee(badEmp));
    }

    @Test //addEmployee
    public void checkingAddEmployee() throws EmployeeValidationException {
        final Employee actual = employee1;
        EmployeeRequest employee = new EmployeeRequest();
        employee.setFirstName(actual.getFIRST_NAME());
        employee.setLastName(actual.getLAST_NAME());
        employee.setSalary(actual.getSALARY());
        employee.setDepartment(actual.getDEPARTMENT());

        Mockito.when(employeeRepository.getLastId()).thenReturn(0);
        Employee expected = employeeService.addEmployee(employee);

        assertEquals(actual,expected);
    }


    @Test //getSalarySum
    public void checkingSalarySum() {

    }

    @Test //getAverageSalary
    public void checkingAverageSalary() {
        final int average = actualEmployee.stream().mapToInt(Employee::getSALARY).sum();
        final List <Employee> expected = actualEmployee.stream().filter(e -> e.getSALARY() > average).collect(Collectors.toList());
        final int actual = employeeService.getAverageSalary();

        assertEquals(actual, expected);

    }

    @Test // getMinSalary
    public void checkingMinSalary() {
        final Employee actual = actualEmployee.stream().max(Comparator.comparing(Employee::getSALARY)).get();
        final int expected = employeeService.getMinSalary();

        assertEquals(actual, expected);

    }

    @Test //getMaxSalary
    public void checkingMaxSalary() {
        final Employee actual = actualEmployee.stream().max(Comparator.comparing(Employee::getSALARY)).get();
        final int expected = employeeService.getMaxSalary();

        assertEquals(actual, expected);


    }

}
