package com.pokemongo.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Pokemon implements Serializable {

    private static final long serialVersionUID = -1790191399726700022L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pokedexNumber;
    private String name;
    private Integer combatPower;
    private Integer healthPoints;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User owner;

    public Long getId() {
        return id;
    }

    public String getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(String pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCombatPower() {
        return combatPower;
    }

    public void setCombatPower(Integer combatPower) {
        this.combatPower = combatPower;
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}