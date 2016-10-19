package com.pokemongo.models.Interfaces;

import com.pokemongo.models.User;

public interface Ownable {
    
    public User getOwner();
    public void setOwner(User user);
}
