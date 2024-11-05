package com.dungoensAndDragons.dungoensAndDragons.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int charStrength;
    private int charDexterity;
    private int charConstitution;
    private int charWisdom;
    private int charCharisma;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
}
