package org.friskycodeur.firsto.dao;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.friskycodeur.firsto.entity.User;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class PostDao {
    private int id;
    private String title;
    private String description;
    private LocalDateTime experienceDate;
    private String location;
    private int userId;
}
