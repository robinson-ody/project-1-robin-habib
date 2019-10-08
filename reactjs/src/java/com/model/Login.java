package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
@Document(collection = "Login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String username;
    private String password;
    private String role;


    public Login(String username, String password, String role){
        this.username=username;
        this.password=password;
        this.role=role;
    }


    public String getRole() { return role; }
    public String getPassword() { return password; }
    public String getUsername() { return username; }
    public String getId() { return id; }
}