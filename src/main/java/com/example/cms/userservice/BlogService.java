package com.example.cms.userservice;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.dto.UserResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogService {


	public ResponseEntity<ResponseStructure<BlogResponse>> registerBlog(int userId, BlogRequest request);

	public ResponseEntity<ResponseStructure<BlogResponse>> findBlog(int blogId);

	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(int blogId, BlogRequest request);

	public ResponseEntity<Boolean> checkTitle(String title);

}
