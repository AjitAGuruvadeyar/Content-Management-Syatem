package com.example.cms.userservice.impl;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.dto.UserResponse;
import com.example.cms.exception.BlogAlreadyExistByTitleException;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.TopicNotSpecifiedException;
import com.example.cms.exception.UserAlreadyExistByEmailException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.usermodel.Blog;
import com.example.cms.usermodel.ContributionPanel;
import com.example.cms.usermodel.User;
import com.example.cms.userrepository.BlogRepository;
import com.example.cms.userrepository.ContributionPanelRepository;
import com.example.cms.userrepository.UserRepository;
import com.example.cms.userservice.BlogService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class BlogServiceImpl implements BlogService{

	private BlogRepository blogRepostiory;
	private UserRepository userRepository;
	private ResponseStructure<BlogResponse> response;
	private ContributionPanelRepository contribustionPanelRepository;

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> registerBlog(int userId, BlogRequest blogRequest) {

		return userRepository.findById(userId).map(user -> {
			if(blogRepostiory.existsByTitle(blogRequest.getTitle()))
				throw new BlogAlreadyExistByTitleException("Failed to register Blog title");

			if(blogRequest.getTopic().length<1)
				throw new TopicNotSpecifiedException("Failed to register Blog topic");

			Blog blog = mapToBlog(blogRequest);
			ContributionPanel panel = contribustionPanelRepository.save(new ContributionPanel());
			blog.setContributionPanel(panel);
			blog.setUsers(user);	
			
			//			blog.setUsers(Arrays.asList(user));

			blogRepostiory.save(blog);

			return ResponseEntity.ok(response.setStatuscode(HttpStatus.OK.value())
					.setMessage("blog Register successfully")
					.setData(mapToResponse(blog)));
		}).orElseThrow(() -> new UserNotFoundByIdException("user not found by id"));


	}


	private BlogResponse mapToResponse(Blog saveBlog) {


		return BlogResponse.builder()
				.blogId(saveBlog.getBlogId())
				.title(saveBlog.getTitle())
				.topics(saveBlog.getTopics())
				.about(saveBlog.getAbout())
				.build();
	}


	private Blog mapToBlog(BlogRequest blogRequest) {

		Blog b=new Blog();
		b.setTitle(blogRequest.getTitle());
		b.setTopics(blogRequest.getTopic());
		b.setAbout(blogRequest.getAbout());

		return b;

	}


	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlog(int blogId) {

		return blogRepostiory.findById(blogId).map(b -> {

			return ResponseEntity.ok(response
					.setStatuscode(HttpStatus.OK.value())
					.setMessage("Blogs fetched successfuly")
					.setData(mapToResponse(b)));
		})
				.orElseThrow(()-> new BlogNotFoundByIdException("blog not found by id"));
	}


	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(int blogId, BlogRequest request) {
		return blogRepostiory.findById(blogId).map(existingBlog -> {
			Blog updatedBlog=mapToBlog(request);
			updatedBlog.setBlogId(blogId);
			blogRepostiory.save(updatedBlog);

			return ResponseEntity.ok(response
					.setStatuscode(HttpStatus.OK.value())
					.setMessage("Blogs updated successfuly")
					.setData(mapToResponse(updatedBlog)));
		})
				.orElseThrow(()-> new BlogNotFoundByIdException("blog not found by id"));

	}


	@Override
	public ResponseEntity<Boolean> checkTitle(String title) {
		boolean blogExists=blogRepostiory.existsByTitle(title);

		return ResponseEntity.ok(blogExists);
	}



}
