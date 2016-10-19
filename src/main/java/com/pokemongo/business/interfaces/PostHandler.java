package com.pokemongo.business.interfaces;

import com.pokemongo.exceptions.DatabaseException;
import com.pokemongo.exceptions.FormException;
import com.pokemongo.exceptions.UserException;
import com.pokemongo.models.Post;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PostHandler {
    
    Post savePost(Post post) throws UserException, DatabaseException, FormException;
    
    Post saveReply(Post reply, long parentId) throws UserException, DatabaseException, FormException;
    
    List<Post> fetchPostsWithoutParent();

    List<Post> fetchPostsOrderedByChildPostsLength();

    List<Post> fetchPostsByKeyword(String keyword) throws FormException;
    
}