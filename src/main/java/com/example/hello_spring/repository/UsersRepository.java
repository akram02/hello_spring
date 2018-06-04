package com.example.hello_spring.repository;

import com.example.hello_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByConfirmationToken(String token);
}
