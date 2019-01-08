package com.future.controller;

import com.future.model.Employee;
import com.future.repository.EmployeeRepository;
import com.future.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getSingleEmployee(@PathVariable("id") String id) {
        return (Employee) employeeRepository.findOne(id);
    }

    @PostMapping("/employee/create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
        Employee employeeData = employeeRepository.findOne(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeData.setEmail(employee.getEmail());
        employeeData.setPassword(employee.getPassword());
        employeeData.setName(employee.getName());
        employeeData.setBirthday(employee.getBirthday());
        employeeData.setGender(employee.getGender());
        employeeData.setDivision(employee.getDivision());
        employeeData.setSuperior(employee.getSuperior());
        employeeData.setRole(employee.getRole());

        Employee updatedemployee = employeeRepository.save(employeeData);
        return new ResponseEntity<>(updatedemployee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) {
        employeeRepository.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }


}
