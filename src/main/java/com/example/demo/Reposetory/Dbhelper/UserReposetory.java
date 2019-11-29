package com.example.demo.Reposetory.Dbhelper;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserReposetory {

    int addUser (User user);
    int deletePost(int id);
    List<User> getAllUsers();
    User findById(int id);
    List<Post> getAllPost ();
    User authenticateUser(String username, String password);
    List<User> fetchAllUsers();
    int create(Post post);
    Post findPostById(int id);
    Optional<User> findUserById(int userId);



}
