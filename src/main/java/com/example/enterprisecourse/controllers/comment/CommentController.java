package com.example.enterprisecourse.controllers.comment;

import com.example.enterprisecourse.models.comments.CommentEntity;
import com.example.enterprisecourse.models.comments.CommentRepository;
import com.example.enterprisecourse.models.posts.PostEntity;
import com.example.enterprisecourse.models.posts.PostRepository;
import com.example.enterprisecourse.models.users.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comments")  // Set a base mapping for the CommentController
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/post/{postId}")
    public String showCommentsForPost(@PathVariable Long postId, Model model) {
        Optional<PostEntity> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            PostEntity post = postOptional.get();
            List<CommentEntity> comments = commentRepository.findByPost(post);
            model.addAttribute("post", post);
            model.addAttribute("comments", comments);
            return "postDetails";  // Assuming you have a template for displaying post details
        } else {
            // Handle post not found
            return "redirect:/forum";
        }
    }

    @PostMapping("/create/{postId}")
    public String createComment(@PathVariable Long postId, @AuthenticationPrincipal UserEntity currentUser,
                                CommentEntity commentEntity) {
        Optional<PostEntity> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            PostEntity post = postOptional.get();
            commentEntity.setCreatedBy(currentUser);
            commentEntity.setCreatedAt(LocalDateTime.now());
            commentEntity.setPost(post);

            // Save the comment to the database
            commentRepository.save(commentEntity);
            return "redirect:/post/" + postId;
        } else {
            // Handle post not found
            return "redirect:/forum";
        }
    }
}
