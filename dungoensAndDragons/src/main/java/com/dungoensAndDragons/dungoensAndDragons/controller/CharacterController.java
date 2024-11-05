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

import com.dungoensAndDragons.dungoensAndDragons.model.Character;
import com.dungoensAndDragons.dungoensAndDragons.service.CharacterService;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public List<Character> getAllCharacters() {
	return characterService.getAllCharacters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
	Optional<Character> character = characterService.getCharacterById(id);
	return character.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Character createCharacter(@RequestBody Character character) {
	return characterService.createCharacter(character);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
	characterService.deleteCharacter(id);
	return ResponseEntity.noContent().build();
    }
}
