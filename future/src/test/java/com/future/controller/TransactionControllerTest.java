package com.future.controller;


import com.future.model.Employee;
import com.future.model.Transaction;
import com.future.repository.EmployeeRepository;
import com.future.repository.TransactionRepository;
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
@SpringBootTest(classes = TransactionControllerTest.class)
public class TransactionControllerTest {
    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionRepository transactionRepository;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTransactionTest(){
        List<Transaction> a = new ArrayList<Transaction>();
        when(transactionRepository.findAll()).thenReturn(a);
        this.transactionController.getAllTransaction();
    }
}
