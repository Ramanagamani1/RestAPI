package com.example.restapi;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@RestController
public class EmployeeController {

    private Map<Integer,Employee> employeeList = new HashMap<>();

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
         return employeeList.values().stream().collect(Collectors.toList());
    }

    @GetMapping("/employee")
    public Employee getEmployee(@RequestParam("id") Integer id) {
        return employeeList.get(id);
    }

    @PostMapping("/employee")
    public void createEmployee(@RequestBody Employee employee) throws Exception {
        if(employee.getId() == null){
            throw new Exception("Employee id cannot be null");
        }
        employeeList.put(employee.getId(), employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@RequestBody Employee employee) throws Exception {
        if(employee.getId() == null || !employeeList.containsKey(employee.getId())){
            throw new Exception("Not a valid employee");
        }
        return employeeList.put(employee.getId(), employee);
    }

    @PatchMapping("/employee/{id}")
    public Employee patchUpdateEmployee(@RequestBody Employee employee, @PathVariable("id") Integer id) throws Exception {
        Employee existingEmployee = employeeList.get(id);

        if(employee.getName()!=null) {
            existingEmployee.setName(employee.getName());
        }

        if(employee.getAge()!=null) {
            existingEmployee.setAge(employee.getAge());
        }

        return employeeList.put(employee.getId(), employee);
    }

    @DeleteMapping("/employee/{id}")
    public Employee deleteEmployee(@PathVariable Integer id){
        return employeeList.remove(id);
    }

}
