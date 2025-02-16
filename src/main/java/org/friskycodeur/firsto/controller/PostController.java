package org.friskycodeur.firsto.controller;

import org.friskycodeur.firsto.dto.PostDto;
import org.friskycodeur.firsto.entity.Post;
import org.friskycodeur.firsto.service.PostService;
import org.friskycodeur.firsto.util.UserContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
public class PostController {

    private final UserContext userContext;
    PostService postService;

    public PostController(PostService postService, UserContext userContext) {
        this.postService = postService;
        this.userContext = userContext;
    }

    @GetMapping("")
    public List<PostDto> getUserExperiences() {
        return postService.getUserExperience(userContext.getCurrentUserId(), null);
    }

    @PostMapping("/add")
    public List<PostDto> addUserExperience(@RequestBody PostDto post) {
        boolean postCreated = postService.createOrUpdatePost(post);
        if (postCreated) {
            return postService.getUserExperience(userContext.getCurrentUserId(), null);
        }
        return null;
    }

    @GetMapping("/{postId}")
    public Post getExperience(@PathVariable int postId) {
        return postService.getExperience(postId);
    }

    @PostMapping("/update")
    public Post updateExperience(@RequestBody PostDto post) {
        boolean postUpdated = postService.createOrUpdatePost(post);
        if (postUpdated) {
            return getExperience(post.getId());
        }
        return null;
    }

    @DeleteMapping("/delete/{postId}")
    public void deleteExperience(@PathVariable int postId) {
        postService.deletePost(postId);
    }

}
