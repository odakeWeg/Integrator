package com.edson.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edson.persistence.model.Session;

public interface SessionRepository extends MongoRepository<Session, String> {
    
}
