package com.kenzie.appserver.config;

import com.kenzie.appserver.repositories.model.GroceryItemRecord;
import com.kenzie.appserver.service.model.GroceryItem;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.annotation.CacheEvict;

import java.util.concurrent.TimeUnit;

public class CacheStore {
    private Cache<String, GroceryItem> cache;

    public CacheStore(int expiry, TimeUnit timeUnit) {
        // initalize the cache
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiry, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public GroceryItem get(String key) {
        // Write your code here
        // Retrieve and return the concert
        return cache.getIfPresent(key);
    }

    public void evict(String key) {
        // Write your code here
        // Invalidate/evict the concert from cache
        cache.invalidate(key);
    }

    public void add(String key, GroceryItem value) {
        // Write your code here
        // Add concert to cache
        cache.put(key, value);
    }
}
