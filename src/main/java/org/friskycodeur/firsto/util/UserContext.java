package org.friskycodeur.firsto.util;

import org.friskycodeur.firsto.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

    private final UserService userService; // Inject your UserDetailsService

    public UserContext(UserService userService) {
        this.userService = userService;
    }

    public int getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.loadUserByUsername(username).getId(); // Assuming getId() exists
    }
}
