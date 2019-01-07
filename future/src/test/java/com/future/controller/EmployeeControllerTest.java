package com.future.controller;

import com.future.model.Employee;
import com.future.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeControllerTest.class)
public class EmployeeControllerTest {
    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeRepository employeeRepository;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeeTest(){
        List<Employee> a = new ArrayList<Employee>();
        when(employeeRepository.findAll()).thenReturn(a);
        this.employeeController.getAllEmployee();
    }
}
