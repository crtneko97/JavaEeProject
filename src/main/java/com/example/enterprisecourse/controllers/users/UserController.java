package com.example.enterprisecourse.controllers.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.enterprisecourse.config.AppPasswordConfig;
import com.example.enterprisecourse.models.posts.PostEntity;
import com.example.enterprisecourse.models.posts.PostRepository;
import com.example.enterprisecourse.models.roles.Roles;
import com.example.enterprisecourse.models.users.UserEntity;
import com.example.enterprisecourse.models.users.UserNotFoundException;
import com.example.enterprisecourse.models.users.UserRepository;
import com.example.enterprisecourse.services.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final AppPasswordConfig appPasswordConfig; // Bcrypt
    private final PostRepository postRepository;
    
    @Autowired
    private UserService userService;
    
    

    @Autowired
    public UserController(UserRepository userRepository, AppPasswordConfig appPasswordConfig, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.appPasswordConfig = appPasswordConfig;
        this.postRepository = postRepository;
    }
    
    
    

    //
    @GetMapping("/users/edit/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model, RedirectAttributes ra) {
        try {
            UserEntity user = userService.getUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("roles", Roles.values());
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "updateUser";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/adminpage";
        }
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute("user") UserEntity updatedUser, Authentication authentication) {
        String username = authentication.getName();
    	
    	try {
            // Ensure the 'id' is set properly
            UserEntity existingUser = userRepository.findByUsername(username);
            // Update only the required fields
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setRole(updatedUser.getRole());
            userRepository.save(existingUser);
            return "redirect:/login";
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // You might want to return a different view or handle the error differently
            return "error";
        }
    }


    
    
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
    	this.userService.deleteUser(id);
    	
    	return "redirect:/adminpage";
    }
 
    
    @GetMapping("/adminpage")
    public String viewAdminPage(UserEntity userEntity, Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        List<PostEntity> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("postEntity", new PostEntity());
        
        return "adminpage"; // Ensure that this matches the Thymeleaf template name
    }


    

    @GetMapping("/register")
    public String registerUserPage(UserEntity userEntity, Model model) {

        model.addAttribute("roles", Roles.values());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid UserEntity userEntity,   // Enables Error Messages
            BindingResult result,           // Ties the object with result
            Roles roles
    ) {

        // Check FOR @Valid Errors
        if (result.hasErrors()) {
            return "register";
        }

        // TODO - Optionally: handle duplicate entries (@Unique PREFERABLY within ENTITY)
        userEntity.setPassword(
                appPasswordConfig.bCryptPasswordEncoder().encode(userEntity.getPassword())
        );

        userEntity.setAccountEnabled(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setCredentialsNonExpired(true);

        userRepository.save(userEntity);

        return "redirect:/login";
    }

}