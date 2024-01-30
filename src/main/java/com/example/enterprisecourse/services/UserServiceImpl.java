package com.example.enterprisecourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.enterprisecourse.models.UserEntity;
import com.example.enterprisecourse.models.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
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

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getUserById(long id) {
		
		Optional < UserEntity > optional = userRepository.findById(id);
		UserEntity userEntity = null;
		if(optional.isPresent()) {
			userEntity = optional.get();
		} else {
			throw new RuntimeException("Couln't find employee error runtime Exception");
		}
		
		
		return userEntity;
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		
		this.userRepository.deleteById(id);
		
	}

	@Override
	public void saveUser(UserEntity user) {
		this.userRepository.save(user);
		
	}
}
