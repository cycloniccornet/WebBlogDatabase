package com.example.demo.Service;


import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Reposetory.Dbhelper.UserReposetory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("UserReposetoryImpl")
    UserReposetory userReposetory;


    public void addUserDb(User user) {
        log.info("Adding user to db");
        userReposetory.addUser(user);
    }

    public List<User> fetchAllUsers (){
        log.info("fetching all users");
        return userReposetory.getAllUsers();
    }

    public User findById(int id){
        log.info("Finding user by id");
        return userReposetory.findById(id);
    }

    public User authorizeUser(String username, String password) {
        log.info("authorizing user");
        return userReposetory.authenticateUser(username, password);
    }

    public Optional<User> findUserById(int userId){
        log.info("Finding user by id");
        return userReposetory.findUserById(userId);
    }



}
