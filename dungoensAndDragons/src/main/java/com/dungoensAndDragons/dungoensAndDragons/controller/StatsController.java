package com.dungoensAndDragons.dungoensAndDragons.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungoensAndDragons.dungoensAndDragons.model.Stats;
import com.dungoensAndDragons.dungoensAndDragons.service.StatsService;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public List<Stats> getAllStats() {
	return statsService.getAllStats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stats> getStatsById(@PathVariable Long id) {
	Optional<Stats> stats = statsService.getStatsById(id);
	return stats.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
