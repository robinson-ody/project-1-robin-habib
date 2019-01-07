package com.future.controller;

import com.future.model.Employee;
import com.future.model.Inventory;
import com.future.repository.EmployeeRepository;
import com.future.repository.InventoryRepository;
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
@SpringBootTest(classes = InventoryControllerTest.class)
public class InventoryControllerTest {
    @InjectMocks
    InventoryController inventoryController;

    @Mock
    InventoryRepository inventoryRepository;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllInventoryTest(){
        List<Inventory> a = new ArrayList<Inventory>();
        when(inventoryRepository.findAll()).thenReturn(a);
        this.inventoryController.getAllInventory();
    }
}