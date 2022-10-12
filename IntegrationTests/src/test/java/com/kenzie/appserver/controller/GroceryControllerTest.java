package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.GroceryItemCreateRequest;
import com.kenzie.appserver.controller.model.GroceryItemUpdateRequest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

        queryUtility.groceryControllerClient.deleteGroceryItemById(createRequest.getGroceryProductName())
                .andExpect(status().isNoContent());
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

        queryUtility.groceryControllerClient.deleteGroceryItemById(createRequest.getGroceryProductName())
                .andExpect(status().isNoContent());
    }

    @Test
    public void can_get_all_grocery_items()throws Exception{
        //GIVEN
        GroceryItemCreateRequest createRequest1 =  new GroceryItemCreateRequest();
        GroceryItemCreateRequest createRequest2 =  new GroceryItemCreateRequest();

        createRequest1.setGroceryProductId(mockNeat.strings().get());
        createRequest1.setGroceryProductName(mockNeat.strings().get());
        createRequest1.setGroceryProductDepartment(mockNeat.strings().get());
        createRequest1.setGroceryProductPrice(mockNeat.doubles().get());
        createRequest1.setGroceryExpirationDate(mockNeat.strings().get());
        createRequest1.setGroceryType(mockNeat.strings().get());
        createRequest1.setInStock(mockNeat.bools().get());
        createRequest1.setQuantityAvailable(mockNeat.ints().get());
        createRequest1.setDiscount(mockNeat.bools().get());

        createRequest2.setGroceryProductId(mockNeat.strings().get());
        createRequest2.setGroceryProductName(mockNeat.strings().get());
        createRequest2.setGroceryProductDepartment(mockNeat.strings().get());
        createRequest2.setGroceryProductPrice(mockNeat.doubles().get());
        createRequest2.setGroceryExpirationDate(mockNeat.strings().get());
        createRequest2.setGroceryType(mockNeat.strings().get());
        createRequest2.setInStock(mockNeat.bools().get());
        createRequest2.setQuantityAvailable(mockNeat.ints().get());
        createRequest2.setDiscount(mockNeat.bools().get());

        queryUtility.groceryControllerClient.createGroceryItem(createRequest1)
                .andExpect(status().isCreated());

        queryUtility.groceryControllerClient.createGroceryItem(createRequest2)
                .andExpect(status().isCreated());

        //WHEN

        queryUtility.groceryControllerClient.getAllGroceryItems()
                .andExpect(status().isOk());

        //THEN

        queryUtility.groceryControllerClient.getGroceryItem(createRequest1.getGroceryProductName())
                .andExpect(status().isOk());

        queryUtility.groceryControllerClient.deleteGroceryItemById(createRequest1.getGroceryProductName())
                .andExpect(status().isNoContent());

        queryUtility.groceryControllerClient.getGroceryItem(createRequest2.getGroceryProductName())
                .andExpect(status().isOk());

        queryUtility.groceryControllerClient.deleteGroceryItemById(createRequest2.getGroceryProductName())
                .andExpect(status().isNoContent());

    }

    @Test
    public void can_update_grocery_item() throws Exception{
        //GIVEN
        GroceryItemCreateRequest createRequest =  new GroceryItemCreateRequest();
        createRequest.setGroceryProductId("900000023455112");
        createRequest.setGroceryProductName("HarryPot Roast");
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
                .andExpect(status().isCreated());

        //THEN
        queryUtility.groceryControllerClient.getGroceryItem(name)
                .andExpect(status().isOk())
                .andExpect(jsonPath("groceryProductId")
                        .value(is(updateRequest.getGroceryProductId())))
                .andExpect(jsonPath("groceryProductName")
                        .value(is(updateRequest.getGroceryProductName())))
                .andExpect(jsonPath("groceryProductDepartment")
                        .value(is(updateRequest.getGroceryProductDepartment())))
                .andExpect(jsonPath("groceryProductPrice")
                        .value(is(updateRequest.getGroceryProductPrice())));

        queryUtility.groceryControllerClient.deleteGroceryItemById(name)
                .andExpect(status().isNoContent());
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
