package com.example.cms.dto;

import java.util.List;

import com.example.cms.usermodel.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContributionPanelRequest {
	private List<User> users;

}
