package com.future.service;

import com.future.model.Employee;
import com.future.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service("EmployeeService")
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll (){
        return employeeRepository.findAll();
    }

    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee){
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

    public Employee singleEmployee (@PathVariable("id") String id){
        return (Employee) employeeRepository.findOne(id);
    }

    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) {
        employeeRepository.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);}
}
