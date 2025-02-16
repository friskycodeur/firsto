package org.friskycodeur.firsto.controller;


import org.friskycodeur.firsto.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    private final UserService userService;

    public BaseController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String welcome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Welcome to Firsto");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("userId", userService.loadUserByUsername(authentication.getName()).getId());
        return "home";
    }

}
