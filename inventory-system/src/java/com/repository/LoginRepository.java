package com.repository;

import com.model.Login;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoginRepository extends MongoRepository<Login,String>
        <entity that you want to store,type of the id>
        {
        List<Login> findByUsername(String username);
        List<Login> findByPassword(String password);
        List<Login> findByRole(String role);
        }