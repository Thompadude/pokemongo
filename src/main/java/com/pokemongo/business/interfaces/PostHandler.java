package com.pokemongo.business.interfaces;

import com.pokemongo.exceptions.UserNotLoggedInException;
import com.pokemongo.models.Post;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PostHandler {

    void savePost(Post post) throws UserNotLoggedInException;

    void saveReply(Post reply, long parentId) throws UserNotLoggedInException;

    List<Post> fetchPostsWithoutParent();

    List<Post> fetchPostsByKeyword(String keyword);

}