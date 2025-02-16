package org.friskycodeur.firsto.controller;

import org.friskycodeur.firsto.dto.UserDto;
import org.friskycodeur.firsto.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/stats")
    public Map<String, Object> getUserStats() {
        return userService.getUserStats();
    }


}
