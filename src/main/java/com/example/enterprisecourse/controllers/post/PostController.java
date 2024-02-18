package com.example.enterprisecourse.controllers.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.enterprisecourse.models.comments.CommentEntity;
import com.example.enterprisecourse.models.posts.PostEntity;
import com.example.enterprisecourse.models.posts.PostRepository;
import com.example.enterprisecourse.models.comments.CommentRepository;  // Import CommentRepository
import com.example.enterprisecourse.models.users.UserEntity;
import com.example.enterprisecourse.services.PostService;

@Controller
public class PostController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;  // Add CommentRepository

    @Autowired PostService postService;
    
    
    public PostController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;  // Initialize CommentRepository
    }

    @GetMapping("/forum")
    public String showForum(Model model) {
        List<PostEntity> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("postEntity", new PostEntity());
        return "forum";
    }

    @GetMapping("/post/{postId}")
    public String showPostDetails(@PathVariable Long postId, Model model) {
        Optional<PostEntity> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            PostEntity post = postOptional.get();

            // Fetch comments associated with the post
            List<CommentEntity> comments = commentRepository.findByPost(post);

            model.addAttribute("post", post);
            model.addAttribute("comments", comments);  // Add comments to the model

            // Add a new CommentEntity to the model
            model.addAttribute("commentEntity", new CommentEntity());

            return "postDetails";
        } else {
            // Handle post not found
            return "redirect:/forum";
        }
    }

    @PostMapping("/createPost")
    public String createPost(@AuthenticationPrincipal UserEntity currentUser, PostEntity postEntity) {
        postEntity.setCreatedBy(currentUser);
        postEntity.setCreatedAt(LocalDateTime.now());
        postRepository.save(postEntity);
        return "redirect:/forum";
    }
    
    @GetMapping("deletePost/{id}")
    public String deletePost(@PathVariable(value = "id") long id) {
    	this.postService.deletePost(id);
    	
    	return "redirect:/adminpage";
    }
}
