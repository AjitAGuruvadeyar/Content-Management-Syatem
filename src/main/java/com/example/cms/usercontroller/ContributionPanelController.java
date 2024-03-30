package com.example.cms.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.ContributionPanelResponse;
import com.example.cms.userservice.ContributionPanelService;
import com.example.cms.userservice.UserService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class ContributionPanelController {
	
	@Autowired
	private ContributionPanelService service;
	
	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(@PathVariable int userId, @PathVariable int panelId){
		return service.addContributor(userId,panelId);
	}

	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteContributor(@PathVariable int userId, @PathVariable int panelId){
		return service.deleteContributor(userId,panelId);
	}

	
}
