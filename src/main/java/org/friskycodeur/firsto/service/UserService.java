package org.friskycodeur.firsto.service;

import org.friskycodeur.firsto.dao.PostDao;
import org.friskycodeur.firsto.dao.UserDao;
import org.friskycodeur.firsto.entity.Post;
import org.friskycodeur.firsto.entity.User;
import org.friskycodeur.firsto.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PostService postService;

    public UserService(UserRepository userRepository, PostService postService) {
        this.postService = postService;
        this.userRepository = userRepository;
    }

    public List<UserDao> getUsers(){
        List<UserDao> users = new ArrayList<>();
        for(User user: userRepository.findAll()){
            users.add(new UserDao(user.getId(), user.getName(), user.getRole()));
        };
        return users;
    }

    public Map<String,Object> getUserStats(){
        int userId = loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        List<PostDao> expList = postService.getUserExperience(userId, null);
        return Map.of(
                "Total Experiences", expList.size(),
                "Most Common Location", expList.stream().
                        collect(Collectors.groupingBy(PostDao::getLocation, Collectors.counting())).
                        entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey),
                "Latest Experience", expList.stream().map(PostDao::getExperienceDate).max(Comparator.naturalOrder()).get()
        );
    }
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
