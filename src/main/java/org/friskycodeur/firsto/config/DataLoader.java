package org.friskycodeur.firsto.config;

import org.friskycodeur.firsto.entity.Post;
import org.friskycodeur.firsto.entity.User;
import org.friskycodeur.firsto.repository.PostRepository;
import org.friskycodeur.firsto.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.postRepository = postRepository;
    }

    @PostConstruct
    public void loadData() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setName("admin");
            user.setPassword(passwordEncoder.encode("admin123")); // Secure password storage
            user.setRole("ADMIN");
            user.setEmail("test@admin.com");
            userRepository.save(user);

            List<Post> posts = List.of(
                    new Post("Skydiving", "Jumped out of a plane for the first time!",
                            LocalDateTime.of(2022, 6, 15, 10, 30),
                            "Dubai, UAE", user),

                    new Post("Learned Guitar", "Played my first song on guitar!",
                            LocalDateTime.of(2023, 1, 10, 18, 0),
                            "New York, USA", user),

                    new Post("Marathon Run", "Completed my first 10K marathon.",
                            LocalDateTime.of(2021, 9, 5, 7, 0),
                            "Boston, USA", user),

                    new Post("Solo Trip", "Traveled alone for the first time.",
                            LocalDateTime.of(2023, 4, 22, 15, 45),
                            "Bali, Indonesia", user),

                    new Post("Published a Book", "Released my first novel!",
                            LocalDateTime.of(2024, 2, 1, 9, 30),
                            "London, UK", user)
            );
            postRepository.saveAll(posts);
        }
    }
}

