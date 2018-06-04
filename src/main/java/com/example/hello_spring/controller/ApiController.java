package com.example.hello_spring.controller;

import com.example.hello_spring.repository.UsersRepository;
import com.example.hello_spring.model.User;
import com.example.hello_spring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
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

