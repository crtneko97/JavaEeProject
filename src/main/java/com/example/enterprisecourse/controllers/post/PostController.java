package com.example.enterprisecourse.controllers.post;

import com.example.enterprisecourse.models.posts.PostEntity;
import com.example.enterprisecourse.models.posts.PostRepository;
import com.example.enterprisecourse.models.users.UserEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/forum")
    public String showForum(Model model) {
        // Retrieve posts from the database and add them to the model
        List<PostEntity> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("postEntity", new PostEntity()); // This is for the create post form
        return "forum";
    }

    @GetMapping("/post/{postId}")
    public String showPostDetails(@PathVariable Long postId, Model model) {
        Optional<PostEntity> postOptional = postRepository.findById(postId);
        
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            return "postDetails";
        } else {
            // Handle post not found
            return "redirect:/forum";
        }
    }

    @PostMapping("/createPost")
    public String createPost(@AuthenticationPrincipal UserEntity currentUser, PostEntity postEntity) {
        // Set other details like createdBy, createdAt, etc.
        postEntity.setCreatedBy(currentUser);
        postEntity.setCreatedAt(LocalDateTime.now());

        // Save the post to the database
        postRepository.save(postEntity);
        return "redirect:/forum";
    }
}
