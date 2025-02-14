package org.friskycodeur.firsto.controller;

import org.friskycodeur.firsto.dao.UserDao;
import org.friskycodeur.firsto.entity.Post;
import org.friskycodeur.firsto.entity.User;
import org.friskycodeur.firsto.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDao> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/stats")
    public Map<String, Object> getUserStats(){
        return userService.getUserStats();
    }


}
