package com.employee.curd.service.controller;

import com.employee.curd.service.cache.EmployeeCache;
import com.employee.curd.service.entity.Employee;
import com.employee.curd.service.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepo repo;
    private final EmployeeCache employeeCache;

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = repo.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
        employeeCache.loadCache();
        Employee employee = employeeCache.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = repo.findAll();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmp(@RequestBody Employee employee) {
        return repo.findById(employee.getId())
                .map(existingEmployee -> {
                    existingEmployee.setEName(employee.getEName());
                    existingEmployee.setEAddress(employee.getEAddress());
                    return ResponseEntity.ok(repo.save(existingEmployee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found");
        }
    }

}
