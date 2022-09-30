package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.GroceryRepository;
import com.kenzie.appserver.repositories.model.GroceryItemRecord;
import com.kenzie.appserver.service.model.GroceryItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class GroceryServiceTest {
    private GroceryRepository groceryRepository;
    private GroceryService groceryService;
    private Calendar date;

    @BeforeEach
    void setup() {
        groceryRepository = mock(GroceryRepository.class);
        groceryService = new GroceryService(groceryRepository);

        date = Calendar.getInstance();
        date.set(Calendar.YEAR, 2022);
        date.set(Calendar.MONTH, Calendar.NOVEMBER);
        date.set(Calendar.DAY_OF_MONTH, 5);
    }
    /** ------------------------------------------------------------------------
     *  GroceryService.findById
     *  ------------------------------------------------------------------------ **/

    @Test
    void findByItemId() {
        // GIVEN
        String id = randomUUID().toString();

        GroceryItemRecord record = new GroceryItemRecord();
        record.setId(id);
        record.setName("Grocery item name");

        // WHEN
        when(groceryRepository.findById(id)).thenReturn(Optional.of(record));
        GroceryItem item = groceryService.findByItemId(id);

        // THEN
        Assertions.assertNotNull(item, "The object is returned");
        Assertions.assertEquals(record.getId(), item.getGroceryProductId(), "The id matches");
        Assertions.assertEquals(record.getName(), item.getGroceryProductName(), "The name matches");
    }

    @Test
    void findByItemId_invalid() {
        // GIVEN
        String id = randomUUID().toString();

        when(groceryRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        GroceryItem item = groceryService.findByItemId(id);

        // THEN
        Assertions.assertNull(item, "The item is null when not found");
    }

    /** ------------------------------------------------------------------------
     *  GroceryService.addNewItem
     *  ------------------------------------------------------------------------ **/

    @Test
    void addNewItem() {
        // GIVEN
        String id = randomUUID().toString();
        Date expiration = date.getTime();

        GroceryItem item = new GroceryItem(id,"Rambutan","Produce",
                6.99,expiration,"Red Fruit",true,65,false);

        ArgumentCaptor<GroceryItemRecord> groceryRecordCaptor = ArgumentCaptor.forClass(GroceryItemRecord.class);

        // WHEN
        GroceryItem returnedItem = groceryService.addNewItem(item);

        // THEN

        Assertions.assertNotNull(returnedItem, "The grocery item was not returned");

        verify(groceryRepository).save(groceryRecordCaptor.getValue());

        Assertions.assertEquals(returnedItem.getGroceryProductId(), item.getGroceryProductId(),
                "expected item ids to match but they do not");

        Assertions.assertEquals(returnedItem.getGroceryProductName(), item.getGroceryProductName(),
                "Expected the item names to match, but they do not");
    }
}
