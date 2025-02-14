package org.friskycodeur.firsto.service;

import jakarta.annotation.Nullable;
import org.friskycodeur.firsto.dao.PostDao;
import org.friskycodeur.firsto.entity.Post;
import org.friskycodeur.firsto.entity.User;
import org.friskycodeur.firsto.enums.ViewType;
import org.friskycodeur.firsto.repository.PostRepository;
import org.friskycodeur.firsto.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostDao> getUserExperience(int userId, @Nullable ViewType viewType){
        List<Post> posts = postRepository.findByUserId(userId);
        return sortPosts(posts, viewType).stream().map(Post::toDao).collect(Collectors.toList());
    }

    public List<Post> sortPosts(List<Post> posts, @Nullable ViewType viewType){
        if(viewType == null) return posts;
        switch (viewType){
            case LOCATION -> posts.sort(Comparator.comparing(Post::getLocation));
            case TIMELINE -> posts.sort(Comparator.comparing(Post::getExperienceDate));
            default -> posts.sort(Comparator.comparing(Post::getTitle));
        }
        return posts;
    }

    public Post getExperience(int postId){
        return postRepository.findById(postId).orElse(null);
    }

    public boolean createOrUpdatePost(PostDao postDao){
        try{
            Post post = postRepository.findById(postDao.getId()).orElse(createPost(postDao));
            postRepository.save(post);
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public Post createPost(PostDao postDao){
        return new Post(postDao.getTitle(), postDao.getDescription(), postDao.getExperienceDate(), postDao.getLocation(),
                userRepository.findById(postDao.getUserId()).orElseThrow(() -> new RuntimeException("User does not exist")));
    }

    public void deletePost(int postId){
        postRepository.deleteById(postId);
    }

}
