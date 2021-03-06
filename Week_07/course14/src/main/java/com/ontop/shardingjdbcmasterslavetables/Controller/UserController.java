package com.ontop.shardingjdbcmasterslavetables.Controller;

import com.ontop.shardingjdbcmasterslavetables.Entity.User;
import com.ontop.shardingjdbcmasterslavetables.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/add")
    public User add(@RequestParam Integer id, @RequestParam String username, @RequestParam String  password) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }


    @GetMapping("/addMore")
    public String addMore(){
        for (int i = 100; i < 150; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("111"+(i));
            user.setPassword("123456");
            userRepository.save(user);
        }

        return "OK";
    }
}
