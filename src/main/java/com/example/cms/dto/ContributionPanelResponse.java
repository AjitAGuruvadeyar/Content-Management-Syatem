package com.example.cms.dto;

import java.util.List;

import com.example.cms.usermodel.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class ContributionPanelResponse {
	private int panelId;
	private List<User> users;
}
