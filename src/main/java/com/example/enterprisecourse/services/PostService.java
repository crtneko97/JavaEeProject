package com.example.enterprisecourse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprisecourse.models.posts.PostEntity;
import com.example.enterprisecourse.models.posts.PostRepository;

@Service
public class PostService {

	@Autowired private PostRepository postRepository;
	
	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public void deletePost(long id) {
		this.postRepository.deleteById(id);
	}
}
