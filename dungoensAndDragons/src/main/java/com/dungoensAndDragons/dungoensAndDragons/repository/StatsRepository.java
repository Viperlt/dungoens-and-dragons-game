package com.dungoensAndDragons.dungoensAndDragons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dungoensAndDragons.dungoensAndDragons.model.Stats;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {
    // You can define custom query methods here if needed.
    // For example:
    // Optional<Player> findByEmail(String email);
}