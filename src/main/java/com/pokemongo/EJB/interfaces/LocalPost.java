package com.pokemongo.EJB.interfaces;

import com.pokemongo.model.Post;

import javax.ejb.Local;

@Local
public interface LocalPost {
    void savePost(Post post);
}
