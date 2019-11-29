package com.example.demo.Service;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUserDb(User user);
    List<User> fetchAllUsers ();
    User findById(int id);
    User authorizeUser(String username, String password);
    Optional<User> findUserById(int userId);


}
