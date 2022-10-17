package com.edson.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edson.persistence.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
}
