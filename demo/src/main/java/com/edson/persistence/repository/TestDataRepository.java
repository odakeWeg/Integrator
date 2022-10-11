package com.edson.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edson.persistence.model.TestData;

public interface TestDataRepository extends MongoRepository<TestData, String> {
    
}
