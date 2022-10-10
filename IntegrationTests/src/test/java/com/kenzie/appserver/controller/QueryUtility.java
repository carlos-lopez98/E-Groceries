package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.GroceryItemCreateRequest;
import com.kenzie.appserver.controller.model.GroceryItemUpdateRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class QueryUtility {

    public GroceryControllerClient groceryControllerClient;

    private final MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    public QueryUtility(MockMvc mvc){
        this.mvc = mvc;
        this.groceryControllerClient =  new GroceryControllerClient();
    }

    public class GroceryControllerClient{
        public ResultActions getGroceryItem(String id) throws Exception{
            return mvc.perform(get("/grocery-item/{id}", id)
                    .accept(MediaType.APPLICATION_JSON));
        }

        public ResultActions getAllGroceryItems() throws Exception{
            return mvc.perform(get("/grocery-item/all")
                    .accept(MediaType.APPLICATION_JSON));
        }

        public ResultActions createGroceryItem(GroceryItemCreateRequest groceryItemCreateRequest) throws Exception{
            return mvc.perform(post("/grocery-item/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(groceryItemCreateRequest)));
        }

        public ResultActions updateGroceryItem(GroceryItemUpdateRequest groceryItemUpdateRequest) throws Exception{
            return mvc.perform(put("/grocery-item/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(groceryItemUpdateRequest)));
        }

        public ResultActions deleteGroceryItemById(String id) throws Exception{
            return mvc.perform(delete("/grocery-item/{id}", id)
                    .accept(MediaType.APPLICATION_JSON));
        }

    }
}