package com.dungoensAndDragons.dungoensAndDragons.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungoensAndDragons.dungoensAndDragons.model.Stats;
import com.dungoensAndDragons.dungoensAndDragons.repository.StatsRepository;

@Service
public class StatsService {

    @Autowired
    private StatsRepository statsRepository;

    public List<Stats> getAllStats() {
	return statsRepository.findAll();
    }

    public Optional<Stats> getStatsById(Long id) {
	return statsRepository.findById(id);
    }
}
