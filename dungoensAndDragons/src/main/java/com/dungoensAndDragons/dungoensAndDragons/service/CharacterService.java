package com.dungoensAndDragons.dungoensAndDragons.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungoensAndDragons.dungoensAndDragons.model.Character;
import com.dungoensAndDragons.dungoensAndDragons.repository.CharacterRepository;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public List<Character> getAllCharacters() {
	return characterRepository.findAll();
    }

    public Optional<Character> getCharacterById(Long id) {
	return characterRepository.findById(id);
    }

    public Character createCharacter(Character character) {
	return characterRepository.save(character);
    }

    public void deleteCharacter(Long id) {
	characterRepository.deleteById(id);
    }
}
