package com.future.repository;

import com.future.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findByUsernameAndPasswordAndEnabled(String username,String password, boolean bool);
}
