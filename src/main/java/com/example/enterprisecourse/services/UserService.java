package com.example.enterprisecourse.services;

import java.util.List;

import com.example.enterprisecourse.models.UserEntity;

public interface UserService {
	
	List <UserEntity> getAllUsers();
	
	UserEntity getUserById(long id);
	
	void deleteUser(long id);
	
	void saveUser(UserEntity user);
	

}
