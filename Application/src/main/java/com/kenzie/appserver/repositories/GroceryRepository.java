package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.GroceryItemRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface GroceryRepository extends CrudRepository<GroceryItemRecord, String> {


}
