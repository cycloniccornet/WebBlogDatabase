package com.example.demo.Service;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Reposetory.Dbhelper.UserReposetory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceimpl implements PostService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("UserReposetoryImpl")
    UserReposetory userReposetory;

   public List<Post> fetchAllPost (){
       log.info("PostServiceimpl: Requesting getAllPost");
    return userReposetory.getAllPost();
   }

   public int createpost(Post post){
       log.info("PostServiceimpl: Requesting createpost");
       return userReposetory.create(post);
   }

   public void deletePost(int id){
       log.info("PostServiceimpl: Requesting Delete Post");
       userReposetory.deletePost(id);
    }

   public Post findPostById(int postId){
       log.info("Finding Post by id");
       return userReposetory.findPostById(postId);
   }

}
