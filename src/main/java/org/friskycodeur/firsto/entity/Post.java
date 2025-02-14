package org.friskycodeur.firsto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.friskycodeur.firsto.dao.PostDao;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;
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

    public PostDao toDao(){
        return new PostDao(getId(),getTitle(),getDescription(), getExperienceDate(), getLocation(), getUser().getId());
    }
}
