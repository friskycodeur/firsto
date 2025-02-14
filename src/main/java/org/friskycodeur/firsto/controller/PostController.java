package org.friskycodeur.firsto.controller;

import org.friskycodeur.firsto.dao.PostDao;
import org.friskycodeur.firsto.entity.Post;
import org.friskycodeur.firsto.service.PostService;
import org.friskycodeur.firsto.util.UserContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
public class PostController {

    PostService postService;
    private final UserContext userContext;

    public PostController(PostService postService, UserContext userContext) {
        this.postService = postService;
        this.userContext = userContext;
    }

    @GetMapping("")
    public List<PostDao> getUserExperiences(){
        return postService.getUserExperience(userContext.getCurrentUserId(), null);
    }

    @PostMapping("/add")
    public List<PostDao> addUserExperience(@RequestBody PostDao post){
        boolean postCreated = postService.createOrUpdatePost(post);
        if(postCreated){
            return postService.getUserExperience(userContext.getCurrentUserId(), null);
        }
        return null;
    }

    @GetMapping("/{postId}")
    public Post getExperience(@PathVariable int postId){
        return postService.getExperience(postId);
    }

    @PostMapping("/update")
    public Post updateExperience(@RequestBody PostDao post){
        boolean postUpdated = postService.createOrUpdatePost(post);
        if(postUpdated){
            return getExperience(post.getId());
        }
        return null;
    }

    @DeleteMapping("/delete/{postId}")
    public void deleteExperience(@PathVariable int postId){
        postService.deletePost(postId);
    }

}
