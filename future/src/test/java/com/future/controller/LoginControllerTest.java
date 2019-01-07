package com.future.controller;

import com.future.model.requestResponse.LoginRequest;
import com.future.model.requestResponse.LoginResponse;
import com.future.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoginControllerTest.class)
public class LoginControllerTest {
    @InjectMocks
    LoginController loginController;
    @Mock
    EmployeeRepository employeeRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_authenticate_employeeDataNull(){
        LoginRequest request = new LoginRequest();
        request.setEmail("a");
        when(this.employeeRepository.findByEmail("a")).thenReturn(null);
        LoginResponse result = this.loginController.authenticate(request);
        assertEquals(result.isSuccess(), false);

    }


}
