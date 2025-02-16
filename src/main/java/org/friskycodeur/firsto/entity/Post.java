package org.friskycodeur.firsto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.friskycodeur.firsto.dto.PostDto;
import org.friskycodeur.firsto.dto.UserDto;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private LocalDateTime experienceDate;
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post(String title, String description, LocalDateTime experienceDate, String location, User user) {
        this.title = title;
        this.description = description;
        this.experienceDate = experienceDate;
        this.location = location;
        this.user = user;
    }

    public PostDto toDao() {
        return new PostDto(getId(), getTitle(), getDescription(), getExperienceDate(), getLocation(), getUserDto());
    }

    public UserDto getUserDto(){
        return new UserDto(this.user.getId(), this.user.getUsername(), this.user.getRole());
    }
}
