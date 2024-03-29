package com.example.cms.dto;

import java.util.List;

import com.example.cms.usermodel.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequest {
	@NotNull(message = "Title must requred")
	@Pattern(regexp = "^[a-zA-Z ]+$")
	private String title;
	private String[] topic;
	private String about;
	

}
