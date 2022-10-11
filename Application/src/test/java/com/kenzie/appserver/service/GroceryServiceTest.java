package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
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
    private CacheStore cacheStore;

    @BeforeEach
    void setup() {
        groceryRepository = mock(GroceryRepository.class);
        cacheStore = mock(CacheStore.class);
        groceryService = new GroceryService(groceryRepository, cacheStore);

    }
    /** ------------------------------------------------------------------------
     *  groceryService.findById
     *  ------------------------------------------------------------------------ **/

    @Test
    void findByItemId() {
        // GIVEN
        String id = randomUUID().toString();

        String name = "pasta";
        GroceryItemRecord record = new GroceryItemRecord();
        record.setId(id);
        record.setName(name);
        record.setDepartment("frozen");
        record.setPrice(5.99);
        record.setExpiration("10/30/2022");
        record.setInStock(true);
        record.setQuantityAvailable(15);
        record.setType("chicken");
        record.setDiscount(false);
        when(groceryRepository.findById(name)).thenReturn(Optional.of(record));

        //WHEN
        GroceryItem item = groceryService.findByItemName(name);

        // THEN
        Assertions.assertNotNull(item, "The object is returned");
        Assertions.assertEquals(record.getId(), item.getGroceryProductId(), "The id matches");
        Assertions.assertEquals(record.getName(), item.getGroceryProductName(), "The name matches");
        Assertions.assertEquals(record.getDepartment(), item.getGroceryProductDepartment(), "Department matches");
        Assertions.assertEquals(record.getPrice(), item.getGroceryProductPrice(), "The price matches");
        Assertions.assertEquals(record.getExpiration(), item.getGroceryExpirationDate(), "The expiration matches");
        Assertions.assertEquals(record.getInStock(), item.getInStock(), "The stock matches");
        Assertions.assertEquals(record.getQuantityAvailable(), item.getQuantityAvailable(), "Quantity matches");
        Assertions.assertEquals(record.getType(), item.getGroceryType(), "Type matches");
        Assertions.assertEquals(record.getDiscount(), item.getDiscount(), "Discount matches");
    }

    @Test
    void findByItemId_invalid() {
        // GIVEN
        String id = randomUUID().toString();
        String name = "pasta";

        when(groceryRepository.findById(name)).thenReturn(Optional.empty());

        // WHEN
        GroceryItem item = groceryService.findByItemName(name);

        // THEN
        Assertions.assertNull(item, "The item is null when not found");
    }

    @Test
    void GroceryService_findGroceryItemById_notNullReturnsCachedItem(){
        //GIVEN
        String id = randomUUID().toString();
        String name = "Rambutan";

        GroceryItem item = new GroceryItem(id,name,"Produce",
                6.99,"10/30/2022","Red Fruit",true,65,false);
        when(cacheStore.get(name)).thenReturn(item);

        //WHEN
        GroceryItem returnedRecord = groceryService.findByItemName(name);

        //THEN
        Assertions.assertEquals(returnedRecord.getGroceryProductId(), item.getGroceryProductId(), "The id matches");
        Assertions.assertEquals(returnedRecord.getGroceryProductName(), item.getGroceryProductName(), "The name matches");
        Assertions.assertEquals(returnedRecord.getGroceryProductDepartment(), item.getGroceryProductDepartment(), "Department matches");
        Assertions.assertEquals(returnedRecord.getGroceryProductPrice(), item.getGroceryProductPrice(), "The price matches");
        Assertions.assertEquals(returnedRecord.getGroceryExpirationDate(), item.getGroceryExpirationDate(), "The expiration matches");
        Assertions.assertEquals(returnedRecord.getInStock(), item.getInStock(), "The stock matches");
        Assertions.assertEquals(returnedRecord.getQuantityAvailable(), item.getQuantityAvailable(), "Quantity matches");
        Assertions.assertEquals(returnedRecord.getGroceryType(), item.getGroceryType(), "Type matches");
        Assertions.assertEquals(returnedRecord.getDiscount(), item.getDiscount(), "Discount matches");
    }

    /** ------------------------------------------------------------------------
     *  groceryService.addNewItem
     *  ------------------------------------------------------------------------ **/

    @Test
    void addNewItem() {
        // GIVEN
        String id = randomUUID().toString();

        String name = "Rambutan";

        GroceryItem item = new GroceryItem(id,name,"Produce",
                6.99,"10/30/2022","Red Fruit",true,65,false);

        ArgumentCaptor<GroceryItemRecord> groceryRecordCaptor = ArgumentCaptor.forClass(GroceryItemRecord.class);

        // WHEN
        GroceryItem returnedItem = groceryService.addNewItem(item);

        // THEN

        Assertions.assertNotNull(returnedItem, "The grocery item was not returned");

        verify(groceryRepository).save(groceryRecordCaptor.capture());

        Assertions.assertEquals(returnedItem.getGroceryProductId(), item.getGroceryProductId(), "The id matches");
        Assertions.assertEquals(returnedItem.getGroceryProductName(), item.getGroceryProductName(), "The name matches");
        Assertions.assertEquals(returnedItem.getGroceryProductDepartment(), item.getGroceryProductDepartment(), "Department matches");
        Assertions.assertEquals(returnedItem.getGroceryProductPrice(), item.getGroceryProductPrice(), "The price matches");
        Assertions.assertEquals(returnedItem.getGroceryExpirationDate(), item.getGroceryExpirationDate(), "The expiration matches");
        Assertions.assertEquals(returnedItem.getInStock(), item.getInStock(), "The stock matches");
        Assertions.assertEquals(returnedItem.getQuantityAvailable(), item.getQuantityAvailable(), "Quantity matches");
        Assertions.assertEquals(returnedItem.getGroceryType(), item.getGroceryType(), "Type matches");
        Assertions.assertEquals(returnedItem.getDiscount(), item.getDiscount(), "Discount matches");
    }

    /** ------------------------------------------------------------------------
     *  groceryService.updateItem
     *  ------------------------------------------------------------------------ **/

    @Test
    void GroceryService_updateItem_updatesItemAndCache(){
        String id = randomUUID().toString();

        String name = "Rambutan";

        GroceryItem item = new GroceryItem(id,name,"Produce",
                6.99,"10/30/2022","Red Fruit",true,65,false);

        GroceryItemRecord groceryItemRecord = new GroceryItemRecord();
        groceryItemRecord.setId(item.getGroceryProductId());
        groceryItemRecord.setName(item.getGroceryProductName());
        groceryItemRecord.setDepartment(item.getGroceryProductDepartment());
        groceryItemRecord.setPrice(item.getGroceryProductPrice());
        groceryItemRecord.setExpiration(item.getGroceryExpirationDate());
        groceryItemRecord.setType(item.getGroceryType());
        groceryItemRecord.setInStock(item.getInStock());
        groceryItemRecord.setQuantityAvailable(item.getQuantityAvailable());
        groceryItemRecord.setDiscount(item.getDiscount());

        when(groceryRepository.existsById(name)).thenReturn(true);

        //WHEN
        groceryService.updateItem(item);

        //THEN
        verify(groceryRepository, times(1)).save(groceryItemRecord);
        verify(cacheStore, times(1)).evict(name);
    }

    /** ------------------------------------------------------------------------
     *  groceryService.deleteGroceryItem
     *  ------------------------------------------------------------------------ **/

    @Test
    void GroceryService_deleteGroceryItem_deletesItemAndEvictsFromCache(){
        //GIVEN
        String id = randomUUID().toString();
        String name = "Rambutan";

        //WHEN
        groceryService.deleteGroceryItem(name);

        //THEN
        verify(groceryRepository, times(1)).deleteById(name);
        verify(cacheStore, times(1)).evict(name);
    }
}
