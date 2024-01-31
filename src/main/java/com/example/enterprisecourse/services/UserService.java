package com.example.enterprisecourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.enterprisecourse.models.UserEntity;
import com.example.enterprisecourse.models.UserNotFoundException;
import com.example.enterprisecourse.models.UserRepository;

import jakarta.persistence.Id;

@Service
public class UserService implements UserDetailsService {

    @Autowired private  UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return userEntity;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    


    public UserEntity getUserById(long id) throws UserNotFoundException {
        try {
            Optional<UserEntity> result = userRepository.findById(id);
            if (result.isPresent()) {
                return result.get();
            }
            throw new UserNotFoundException("Could not find any users with ID " + id);
        } catch (UserNotFoundException e) {
            // Log the exception
            // logger.error("Error while fetching user by ID: " + id, e);
            throw e; // Re-throw the exception if needed
        }
    }


    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public void saveUser(UserEntity user) {
        try {
            if (user.getId() > 0) {
                // If id is greater than 0, it's an update
                UserEntity existingUser = userRepository.findById(user.getId())
                        .orElseThrow(() -> new UserNotFoundException("Invalid user Id: " + user.getId()));

                // Update the existing user
                existingUser.setUsername(user.getUsername());
                existingUser.setRole(user.getRole());
                // Update other fields as needed

                userRepository.save(existingUser);
            } else {
                // If id is not greater than 0, it's a new user
                userRepository.save(user);
            }
        } catch (UserNotFoundException e) {
            // Handle the exception, you might want to log or rethrow it
            e.printStackTrace(); // This is just an example, consider using a proper logging mechanism
        }
    }




}

