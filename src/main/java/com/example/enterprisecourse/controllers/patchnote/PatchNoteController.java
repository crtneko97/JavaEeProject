package com.example.enterprisecourse.controllers.patchnote;

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

import com.example.enterprisecourse.models.patchnotes.*;
import com.example.enterprisecourse.models.posts.PostEntity;
import com.example.enterprisecourse.models.users.UserEntity;
import com.example.enterprisecourse.services.PatchService;

@Controller
public class PatchNoteController {

    private final PatchNotesRepository patchNotesRepository;

    @Autowired 
    private PatchService patchService;
    
    
    public PatchNoteController(PatchNotesRepository patchNotesRepository) {
        this.patchNotesRepository = patchNotesRepository;
    }

    @GetMapping("/")
    public String showForum(Model model) {
        List<PatchNotesEntity> patches = patchNotesRepository.findAll();
        model.addAttribute("patches", patches);
        model.addAttribute("patchNotesEntity", new PatchNotesEntity());
        return "home";
    }


    @PostMapping("/createPatchNote")
    public String createPatchNote(@AuthenticationPrincipal UserEntity currentUser, PatchNotesEntity patchNotesEntity) {
        try {
            // Set creator and creation timestamp
            patchNotesEntity.setCreatedBy(currentUser);
            patchNotesEntity.setCreatedAt(LocalDateTime.now());
            
            // Save the new patch note
            patchNotesRepository.save(patchNotesEntity);
            
            // Redirect to admin page
            return "redirect:/adminpage";
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // You might want to return a different view or handle the error differently
            return "error";
        }
    }

}
