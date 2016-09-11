package com.pokemongo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pokemon implements Serializable{
    
    private static final long serialVersionUID = -1790191399726700022L;
    
    @Id
    private Long id;
    private Integer pokedexNumber;
    private String name;
    private Integer cp;
    private Integer hp;
    @ManyToOne
    @JoinColumn(name="ownerId")
    private User owner;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Integer getCp() {
        return cp;
    }
    public void setCp(Integer cp) {
        this.cp = cp;
    }
    public Integer getHp() {
        return hp;
    }
    public void setHp(Integer hp) {
        this.hp = hp;
    }
    

}
