package com.future.repository;

import com.future.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UsersRepository extends MongoRepository<Users, String> {
    Users findByUsername(String username);
}