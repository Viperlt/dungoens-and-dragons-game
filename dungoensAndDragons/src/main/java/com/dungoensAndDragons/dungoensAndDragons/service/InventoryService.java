package com.dungoensAndDragons.dungoensAndDragons.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dungoensAndDragons.dungoensAndDragons.model.Inventory;
import com.dungoensAndDragons.dungoensAndDragons.repository.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() {
	return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id) {
	return inventoryRepository.findById(id);
    }

    public Inventory createInventory(Inventory inventory) {
	return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
	inventoryRepository.deleteById(id);
    }
}
