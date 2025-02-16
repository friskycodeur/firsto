package org.friskycodeur.firsto.service;

import org.friskycodeur.firsto.dto.PostDto;
import org.friskycodeur.firsto.dto.UserDto;
import org.friskycodeur.firsto.entity.User;
import org.friskycodeur.firsto.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(user -> new UserDto(user.getId(), user.getName(), user.getRole())).collect(Collectors.toList());
    }

    public Map<String, Object> getUserStats() {
        int userId = loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        List<PostDto> expList = postService.getUserExperience(userId, null);
        return Map.of(
                "Total Experiences", expList.size(),
                "Most Common Location", expList.stream().
                        collect(Collectors.groupingBy(PostDto::getLocation, Collectors.counting())).
                        entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey),
                "Latest Experience", expList.stream().map(PostDto::getExperienceDate).max(Comparator.naturalOrder()).get()
        );
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
