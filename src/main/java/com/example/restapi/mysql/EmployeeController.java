package com.example.restapi.mysql;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class EmployeeController {

    DBOperations dbOperations;

    public EmployeeController() {
        this.dbOperations = new DBOperations();
    }

    @PostMapping("/api/employees")
    public void createEmployee(@RequestBody Employee employee) throws SQLException {
        dbOperations.createEmployee(employee);
    }

    @GetMapping("/api/employees/{id}")
    public Employee getEmployee(@PathVariable int id) throws SQLException {
        return dbOperations.getEmployee(id);
    }

    @GetMapping("/api/employees")
    public List<Employee> getEmployees() throws SQLException {
        return dbOperations.getEmployees();
    }

    @PutMapping("/api/employees/{id}")
    public void updateEmployee(@RequestBody Employee employee,@PathVariable int id) throws SQLException {
        dbOperations.updateEmployee(id,employee);
    }

    @DeleteMapping("/api/employees/{id}")
    public void deleteEmployee(@PathVariable int id) throws SQLException {
        dbOperations.deleteEmployee(id);
    }
}
