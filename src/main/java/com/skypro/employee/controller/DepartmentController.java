package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/employees")
    public Set <Integer> getAllEmployeesByDepartment() {
        return this.departmentService.getAllEmployeeByDep();
    }

    @GetMapping("/employees/grouped")
    public Map <Integer, List<Employee>> getAllEmployeesByDepNumb() {
        return this.departmentService.getAllEmployeeByDepNumb();
    }

    @GetMapping("/id/salary/sum")
    public int getSalarySumByDepartment(@PathVariable ("id") int id) {
        return this.departmentService.getSalarySumByDep(id);
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryByDepartment(@PathVariable ("id") int id) {
        return this.departmentService.getMaxSalaryByDep(id);
    }

    @GetMapping("/{id}/salary/min")
    public int getMinSalaryByDepartment(@PathVariable ("id") int id) {
        return this.departmentService.getMinSalaryByDep(id);
    }


}
