package org.friskycodeur.firsto.controller;

import org.friskycodeur.firsto.dto.PostDto;
import org.friskycodeur.firsto.service.PostService;
import org.friskycodeur.firsto.util.UserContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/experience")
public class PostController {

    private final UserContext userContext;
    PostService postService;

    public PostController(PostService postService, UserContext userContext) {
        this.postService = postService;
        this.userContext = userContext;
    }

    @GetMapping("")
    public ResponseEntity<?> getUserExperiences() {
        return ResponseEntity.ok().body(postService.getUserExperience(userContext.getCurrentUserId(), null));
    }

    @PostMapping("")
    public ResponseEntity<?> addUserExperience(@RequestBody PostDto post) {
        boolean postCreated = postService.createOrUpdatePost(post);
        if (postCreated) {
            return ResponseEntity.ok().body(postService.getUserExperience(userContext.getCurrentUserId(), null));
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("")
    public ResponseEntity<?> updateExperience(@RequestBody PostDto post) {
        boolean postUpdated = postService.createOrUpdatePost(post);
        if (postUpdated) {
            return ResponseEntity.ok().body(getExperience(post.getId()));
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getExperience(@PathVariable int postId) {
        return ResponseEntity.ok().body(postService.getExperience(postId));
    }

    @DeleteMapping("/{postId}")
    public void deleteExperience(@PathVariable int postId) {
        postService.deletePost(postId);
    }

}
