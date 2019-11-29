package com.example.demo.Service;

import com.example.demo.Model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> fetchAllPost();
    int createpost(Post post);
    void deletePost(int id);
    Post findPostById(int postId);
}
