package com.example.hello_spring.controller;

import com.example.hello_spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/")
    List<User> all(){
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    User getOne(@PathVariable int id){
        return usersRepository.getOne(id);
    }

    @PostMapping("/new")
    void create(@RequestBody User user){
        usersRepository.save(user);
    }

    @PutMapping("/update/{id}")
    void update(@PathVariable int id, @RequestBody User user){
        User temp = usersRepository.getOne(id);
        temp.setName(user.getName());
        temp.setPassword(user.getPassword());
        usersRepository.save(temp);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable int id){
        usersRepository.deleteById(id);
    }

    @GetMapping("/save")
    void save(){
        usersRepository.save(new User());
    }

}

@Repository
interface UsersRepository extends JpaRepository<User, Integer> {

}