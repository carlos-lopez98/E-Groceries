package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.GroceryItemCreateRequest;
import com.kenzie.appserver.controller.model.GroceryItemUpdateRequest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Ask JUnit to create only one instance of the test class and reuse it between tests
public class GroceryControllerTest {

    @Autowired //allows Spring to resolve and inject collaborating beans into our bean
    private MockMvc mvc;

    private final MockNeat mockNeat = MockNeat.threadLocal();
    private QueryUtility queryUtility;

    @BeforeAll
    public void setup(){queryUtility = new QueryUtility(mvc);}

    @Test
    public void can_create_grocery_item() throws Exception{
        //GIVEN
        GroceryItemCreateRequest createRequest =  new GroceryItemCreateRequest();
        createRequest.setGroceryProductId(mockNeat.strings().get());
        createRequest.setGroceryProductName(mockNeat.strings().get());
        createRequest.setGroceryProductDepartment(mockNeat.strings().get());
        createRequest.setGroceryProductPrice(mockNeat.doubles().get());
        createRequest.setGroceryExpirationDate(mockNeat.strings().get());
        createRequest.setGroceryType(mockNeat.strings().get());
        createRequest.setInStock(mockNeat.bools().get());
        createRequest.setQuantityAvailable(mockNeat.ints().get());
        createRequest.setDiscount(mockNeat.bools().get());

        //WHEN
        //THEN
        queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                .andExpect(status().isCreated());
    }

    @Test //Need to create getGroceryProductId, currently using getGroceryProductName
    public void can_get_grocery_item()throws Exception{
        //GIVEN
        GroceryItemCreateRequest createRequest =  new GroceryItemCreateRequest();
        createRequest.setGroceryProductId(mockNeat.strings().get());
        createRequest.setGroceryProductName(mockNeat.strings().get());
        createRequest.setGroceryProductDepartment(mockNeat.strings().get());
        createRequest.setGroceryProductPrice(mockNeat.doubles().get());
        createRequest.setGroceryExpirationDate(mockNeat.strings().get());
        createRequest.setGroceryType(mockNeat.strings().get());
        createRequest.setInStock(mockNeat.bools().get());
        createRequest.setQuantityAvailable(mockNeat.ints().get());
        createRequest.setDiscount(mockNeat.bools().get());

        queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                .andExpect(status().isCreated());

        //WHEN
        //THEN
        queryUtility.groceryControllerClient.getGroceryItem(createRequest.getGroceryProductName())
                .andExpect(status().isOk())
                .andExpect(jsonPath("groceryProductName")
                        .value(is(createRequest.getGroceryProductName())));


    }

    @Test //Need to create getGroceryProductId, currently using getGroceryProductName
    public void can_get_all_grocery_items()throws Exception{
        //GIVEN
        GroceryItemCreateRequest createRequest =  new GroceryItemCreateRequest();
        List<GroceryItemCreateRequest> createRequestList =  new ArrayList<>();

        int i = 0;
        while(i < 3) {
            createRequest.setGroceryProductId(mockNeat.strings().get());
            createRequest.setGroceryProductName(mockNeat.strings().get());
            createRequest.setGroceryProductDepartment(mockNeat.strings().get());
            createRequest.setGroceryProductPrice(mockNeat.doubles().get());
            createRequest.setGroceryExpirationDate(mockNeat.strings().get());
            createRequest.setGroceryType(mockNeat.strings().get());
            createRequest.setInStock(mockNeat.bools().get());
            createRequest.setQuantityAvailable(mockNeat.ints().get());
            createRequest.setDiscount(mockNeat.bools().get());
            createRequestList.add(createRequest);

            queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                    .andExpect(status().isCreated());
            i++;
        }

        //WHEN
        //THEN
        queryUtility.groceryControllerClient.getAllGroceryItems()
                .andExpect(status().isOk());

        for(GroceryItemCreateRequest request : createRequestList) {
            queryUtility.groceryControllerClient.getGroceryItem(request.getGroceryProductName())//Need to create getGroceryProductId
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void can_update_grocery_item() throws Exception{
        //GIVEN
        GroceryItemCreateRequest createRequest =  new GroceryItemCreateRequest();
        createRequest.setGroceryProductId(mockNeat.strings().get());
        createRequest.setGroceryProductName(mockNeat.strings().get());
        createRequest.setGroceryProductDepartment(mockNeat.strings().get());
        createRequest.setGroceryProductPrice(mockNeat.doubles().get());
        createRequest.setGroceryExpirationDate(mockNeat.strings().get());
        createRequest.setGroceryType(mockNeat.strings().get());
        createRequest.setInStock(mockNeat.bools().get());
        createRequest.setQuantityAvailable(mockNeat.ints().get());
        createRequest.setDiscount(mockNeat.bools().get());

        String name = createRequest.getGroceryProductName();

        queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                .andExpect(status().isCreated());

        //WHEN
        GroceryItemUpdateRequest updateRequest = new GroceryItemUpdateRequest();
        updateRequest.setGroceryProductId("1100510");
        updateRequest.setGroceryProductName(name);
        updateRequest.setGroceryProductDepartment("Meat");
        updateRequest.setGroceryProductPrice(35.00);
        updateRequest.setGroceryExpirationDate("10/30/2022");
        updateRequest.setGroceryType("sausage");
        updateRequest.setInStock(true);
        updateRequest.setQuantityAvailable(29);
        updateRequest.setDiscount(true);

        queryUtility.groceryControllerClient.updateGroceryItem(updateRequest)
                .andExpect(status().isOk());

        //THEN
        queryUtility.groceryControllerClient.getGroceryItem(createRequest.getGroceryProductName())
                .andExpect(status().isOk())
                .andExpect(jsonPath("groceryProductId")
                        .value(is(updateRequest.getGroceryProductId())))
                .andExpect(jsonPath("groceryProductName")
                        .value(is(updateRequest.getGroceryProductName())))
                .andExpect(jsonPath("groceryProductDepartment")
                        .value(is(updateRequest.getGroceryProductDepartment())))
                .andExpect(jsonPath("groceryProductPrice")
                        .value(is(updateRequest.getGroceryProductPrice())));
    }

    @Test
    public void can_delete_grocery_item() throws Exception{
        //GIVEN
        GroceryItemCreateRequest createRequest =  new GroceryItemCreateRequest();
        createRequest.setGroceryProductId(mockNeat.strings().get());
        createRequest.setGroceryProductName(mockNeat.strings().get());
        createRequest.setGroceryProductDepartment(mockNeat.strings().get());
        createRequest.setGroceryProductPrice(mockNeat.doubles().get());
        createRequest.setGroceryExpirationDate(mockNeat.strings().get());
        createRequest.setGroceryType(mockNeat.strings().get());
        createRequest.setInStock(mockNeat.bools().get());
        createRequest.setQuantityAvailable(mockNeat.ints().get());
        createRequest.setDiscount(mockNeat.bools().get());

        queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                .andExpect(status().isCreated());

        //WHEN
        queryUtility.groceryControllerClient.deleteGroceryItemById(createRequest.getGroceryProductName())
                        .andExpect(status().isNoContent());

        //THEN
        queryUtility.groceryControllerClient.getGroceryItem(createRequest.getGroceryProductName())
                .andExpect(status().isNotFound());
    }

}
