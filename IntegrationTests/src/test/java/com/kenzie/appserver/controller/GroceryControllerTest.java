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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        createRequest.setGroceryExpirationDate(Date.from(Instant.from(mockNeat.localDates().get())));
        createRequest.setGroceryType(mockNeat.strings().get());
        createRequest.setInStock(mockNeat.bools().get());
        createRequest.setQuantityAvailable(mockNeat.ints().get());
        createRequest.setDiscount(mockNeat.bools().get());

        //WHEN
        //THEN
        queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                .andExpect(status().isOk());
    }

    @Test //Need to create getGroceryProductId, currently using getGroceryProductName
    public void can_get_grocery_item()throws Exception{
        //GIVEN
        GroceryItemCreateRequest createRequest =  new GroceryItemCreateRequest();
        createRequest.setGroceryProductId(mockNeat.strings().get());
        createRequest.setGroceryProductName(mockNeat.strings().get());
        createRequest.setGroceryProductDepartment(mockNeat.strings().get());
        createRequest.setGroceryProductPrice(mockNeat.doubles().get());
        createRequest.setGroceryExpirationDate(Date.from(Instant.from(mockNeat.localDates().get())));
        createRequest.setGroceryType(mockNeat.strings().get());
        createRequest.setInStock(mockNeat.bools().get());
        createRequest.setQuantityAvailable(mockNeat.ints().get());
        createRequest.setDiscount(mockNeat.bools().get());

        queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                .andExpect(status().isOk());

        //WHEN
        //THEN
        queryUtility.groceryControllerClient.getGroceryItem(createRequest.getGroceryProductId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("groceryProductId")
                        .value(is(createRequest.getGroceryProductId())));


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
            createRequest.setGroceryExpirationDate(Date.from(Instant.from(mockNeat.localDates().get())));
            createRequest.setGroceryType(mockNeat.strings().get());
            createRequest.setInStock(mockNeat.bools().get());
            createRequest.setQuantityAvailable(mockNeat.ints().get());
            createRequest.setDiscount(mockNeat.bools().get());
            createRequestList.add(createRequest);

            queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                    .andExpect(status().isOk());
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
        createRequest.setGroceryExpirationDate(Date.from(Instant.from(mockNeat.localDates().get())));
        createRequest.setGroceryType(mockNeat.strings().get());
        createRequest.setInStock(mockNeat.bools().get());
        createRequest.setQuantityAvailable(mockNeat.ints().get());
        createRequest.setDiscount(mockNeat.bools().get());

        String id = createRequest.getGroceryProductId();

        queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                .andExpect(status().isOk());

        //WHEN
        GroceryItemUpdateRequest updateRequest = new GroceryItemUpdateRequest();
        updateRequest.setGroceryProductId(id);
        updateRequest.setGroceryProductName("Filet Mignon");
        updateRequest.setGroceryProductDepartment("Meat");
        updateRequest.setGroceryProductPrice(35.00);

        queryUtility.groceryControllerClient.updateGroceryItem(updateRequest)
                .andExpect(status().isOk());

        //THEN
        queryUtility.groceryControllerClient.getGroceryItem(id)
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
        createRequest.setGroceryExpirationDate(Date.from(Instant.from(mockNeat.localDates().get())));
        createRequest.setGroceryType(mockNeat.strings().get());
        createRequest.setInStock(mockNeat.bools().get());
        createRequest.setQuantityAvailable(mockNeat.ints().get());
        createRequest.setDiscount(mockNeat.bools().get());

        queryUtility.groceryControllerClient.createGroceryItem(createRequest)
                .andExpect(status().isOk());

        //WHEN
        queryUtility.groceryControllerClient.deleteGroceryItemById(createRequest.getGroceryProductId())
                        .andExpect(status().isOk());

        //THEN
        queryUtility.groceryControllerClient.getGroceryItem(createRequest.getGroceryProductId())
                .andExpect(status().isBadRequest());
    }

}
