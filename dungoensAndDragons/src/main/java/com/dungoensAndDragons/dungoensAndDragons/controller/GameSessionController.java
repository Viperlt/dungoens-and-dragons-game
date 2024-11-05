package com.dungoensAndDragons.dungoensAndDragons.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungoensAndDragons.dungoensAndDragons.model.GameSession;
import com.dungoensAndDragons.dungoensAndDragons.service.GameSessionService;

@RestController
@RequestMapping("/api/gameSessions")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;

    @GetMapping
    public List<GameSession> getAllGameSessions() {
	return gameSessionService.getAllGameSessions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameSession> getGameSessionById(@PathVariable Long id) {
	Optional<GameSession> gameSession = gameSessionService.getGameSessionById(id);
	return gameSession.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public GameSession createGameSession(@RequestBody GameSession gameSession) {
	return gameSessionService.createGameSession(gameSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameSession(@PathVariable Long id) {
	gameSessionService.deleteGameSession(id);
	return ResponseEntity.noContent().build();
    }
}
