package com.dungoensAndDragons.dungoensAndDragons.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungoensAndDragons.dungoensAndDragons.model.GameSession;
import com.dungoensAndDragons.dungoensAndDragons.repository.GameSessionRepository;

@Service
public class GameSessionService {

    @Autowired
    private GameSessionRepository gameSessionRepository;

    public List<GameSession> getAllGameSessions() {
	return gameSessionRepository.findAll();
    }

    public Optional<GameSession> getGameSessionById(Long id) {
	return gameSessionRepository.findById(id);
    }

    public GameSession createGameSession(GameSession gameSession) {
	return gameSessionRepository.save(gameSession);
    }

    public void deleteGameSession(Long id) {
	gameSessionRepository.deleteById(id);
    }
}
