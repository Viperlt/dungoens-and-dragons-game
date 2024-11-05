package com.dungoensAndDragons.dungoensAndDragons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dungoensAndDragons.dungoensAndDragons.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can define custom query methods here if needed.
    // For example:
    User findByUsername(String username);
}
