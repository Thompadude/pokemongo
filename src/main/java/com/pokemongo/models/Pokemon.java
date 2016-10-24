package com.pokemongo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pokemongo.models.Interfaces.Ownable;
import com.pokemongo.utilities.RestLink;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Pokemon.fetchAll", query = "SELECT p FROM Pokemon p"),
        @NamedQuery(name = "Pokemon.fetchNewest", query = "SELECT p FROM Pokemon p WHERE p.timeAdded > :date"),
        @NamedQuery(name = "Pokemon.fetchPokemonByOwnerId", query = "SELECT p FROM Pokemon p WHERE p.owner.id=:id ORDER BY p.pokedexNumber")
})
public class Pokemon implements Serializable, Ownable {

    private static final long serialVersionUID = -1790191399726700022L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer pokedexNumber;
    private String name;
    private Integer combatPower;
    private Integer healthPoints;
    private String lng;
    private String lat;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    @JsonIgnore // Do not serialize this - it will cause infinite recursion
    private User owner;
    private LocalDateTime timeAdded;

    @Transient // This is for json serialization, not for database
    private Long ownerId;
    @Transient
    private List<RestLink> restLinks = new ArrayList<>();

    public Pokemon() {
    }

    public Pokemon(Integer pokedexNumber, String name, String lng, String lat, Integer combatPower, Integer healthPoints) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.lng = lng;
        this.lat = lat;
        this.combatPower = combatPower;
        this.healthPoints = healthPoints;
    }

    public Long getId() {
        return id;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(Integer pokedexNumber) {
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

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Long getOwnerId() {
        ownerId = owner.getId();
        return ownerId;
    }
    
    public List<RestLink> getRestLinks() {
        return restLinks;
    }
    
    public void setRestLinks(List<RestLink> restLinks) {
        this.restLinks = restLinks;
    }
    
    public void addRestLink(RestLink restLink) {
        this.restLinks.add(restLink);
    }
}