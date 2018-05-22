package com.example.hello_spring.model;

import javax.persistence.*;

@Entity @Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "users_id")
    private int id;

    @Column(name = "users_name")
    private String name;

    @Column(name = "users_password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
