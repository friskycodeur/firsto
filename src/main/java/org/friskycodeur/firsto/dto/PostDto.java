package org.friskycodeur.firsto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class PostDto {
    private int id;
    private String title;
    private String description;
    private LocalDateTime experienceDate;
    private String location;
    private UserDto user;
}
