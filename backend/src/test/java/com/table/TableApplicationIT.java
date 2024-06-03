package com.table;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TableApplicationIT {

    @Autowired
    public MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
    }

    @Test
    public void getAllRestaurants() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/restaurants");

        ResultActions responseString = mockMvc.perform(request)
                .andExpect(status().is4xxClientError());



    }

    @Test
    public void getRestaurantFromDB() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/restaurants/e40856d1-3152-4e65-bbe8-c3f0a1db445b");

        String responseString = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(responseString);

        assertEquals("Mexicana Cantina" );
    }

}
