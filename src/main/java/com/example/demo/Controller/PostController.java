package com.example.demo.Controller;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Service.PostService;
import com.example.demo.Service.UserService;
import com.example.demo.Service.UserServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/blog/{userId}")
    public String blog(Model model, @PathVariable int userId){
        log.info("Getmapping blog");

        User currentuser = userService.findById(userId);

        model.addAttribute("user", currentuser);



        List<Post> postList = postService.fetchAllPost();
        model.addAttribute("getAllPost", postList);

        return "blog";
    }

    @GetMapping("/addpost/{userId}")
    public String addpostForm(Model model,@PathVariable int userId){
        log.info("Getmapping addPost called...");

        Optional<User> user = userService.findUserById(userId);

        //User user = userService.findUserById(id);
        //model.addAttribute("user", user);

        model.addAttribute("user", user.get());

        model.addAttribute("post", new Post());

        return "addpost";
    }

    @PostMapping("/addpost/{userId}")
    public String addpost(@ModelAttribute Post post, @PathVariable int userId ,Model model){
        log.info("Postmapping add post called...");

        User user = userService.findById(userId);
        model.addAttribute("user", user);

        model.addAttribute("post", post);
        postService.createpost(post);
        log.info("PostMapping addpost færdigt");
        return "redirect:/blog/{userId}";
    }

    @GetMapping("/delete/{id}/{userId}")
    public String deleteForm(@PathVariable int id ,@PathVariable int userId, Model model) {
        log.info("delete action called...");

        Post post = postService.findPostById(id);
        model.addAttribute("post", post);

        User user = userService.findById(userId);
        model.addAttribute("user", user);

        return "delete";
    }

    @PostMapping("/delete/{userId}")
    public String delete(@ModelAttribute Post post, Model model, @PathVariable int userId){
        log.info("Går ind i postmappingen for delete");
        User user = userService.findById(userId);
        model.addAttribute("user", user);

        postService.deletePost(post.getPostId());

        return "redirect:/blog/{userId}";
    }


}
