package com.example.cms.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.userservice.BlogService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> registerBlog(@PathVariable int userId,@RequestBody BlogRequest request){
		return blogService.registerBlog(userId,request);

	}
	
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlog(@PathVariable int blogId){
		return blogService.findBlog(blogId);

	}
	
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(@PathVariable int blogId,@RequestBody BlogRequest request){
		return blogService.updateBlog(blogId,request);

	}
	
	@GetMapping("/titles/{title}/blogs")
	public ResponseEntity<Boolean> checkTitle(@PathVariable String title){
		return blogService.checkTitle(title);

	}
}

