package com.example.demo.Controller;


import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        log.info("Get mapping login");
        User user = new User();
        model.addAttribute(user);
        return "login";
    }


    @PostMapping("/login")
    public String checkLogin(@ModelAttribute User user, Model model){
        log.info("Post mapping login");

        User currentUser = userService.authorizeUser(user.getUsername(), user.getPassword());
        if(currentUser != null) {
            model.addAttribute("user", currentUser);
            return "redirect:/blog/" +  currentUser.getUserId();
        } else {
            return "redirect:/login";
        }
    }



}
