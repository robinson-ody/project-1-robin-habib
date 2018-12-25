/*
package com.future.controller;

import com.future.model.Inventory;
import com.future.repository.InventoryRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class InventoryControllerTest {

    @Test
    public void createInventory() throws Exception {
        Inventory mockInventory = new Inventory("InvenId-1", "UnitTest", 99, 1000);

        // inventoryService.addInventory to respond back with mockInventory
        Mockito.when(
                InventoryRepository.addInventory(Mockito.anyString(),
                        Mockito.any(Inventory.class))).thenReturn(mockInventory);

        // Send inventory as body to /api/test/inventory
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/test/inventory")
                .accept(MediaType.APPLICATION_JSON).content(exampleInventoryJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/api/test/inventory",
                response.getHeader(HttpHeaders.LOCATION));

    }
}
*/
