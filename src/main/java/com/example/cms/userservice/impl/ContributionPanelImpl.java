package com.example.cms.userservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogResponse;
import com.example.cms.dto.ContributionPanelResponse;
import com.example.cms.exception.ContributionPanelNotFindByIdExceptin;
import com.example.cms.exception.IllagalAccessRequestException;
import com.example.cms.usermodel.ContributionPanel;
import com.example.cms.userrepository.BlogRepository;
import com.example.cms.userrepository.ContributionPanelRepository;
import com.example.cms.userrepository.UserRepository;
import com.example.cms.userservice.ContributionPanelService;
import com.example.cms.utility.ResponseStructure;
import com.example.cms.exception.UserNotFoundByIdException;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ContributionPanelImpl implements ContributionPanelService {
	
	private ContributionPanelRepository contributionPanelRepository;

	private UserRepository userRepository;

	private BlogRepository blogRepository;

	private ResponseStructure<ContributionPanelResponse> structure;



	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(int userId, int panelId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
        
		return userRepository.findByEmail(email).map(owner -> {
			  return contributionPanelRepository.findById(panelId).map(panel -> {
				if(!blogRepository.existsByUsersAndContributionPanel(owner, panel))
					throw new IllagalAccessRequestException("contributor not inserted");
				return userRepository.findById(userId).map(contributor->{
					panel.getUser().add(contributor);
					contributionPanelRepository.save(panel);
					
					return ResponseEntity.ok(structure
							.setData(mapToContributeResponse(panel))
							.setMessage("Contributor inserted")
							.setStatuscode(HttpStatus.OK.value()));
				}).orElseThrow(()-> new UserNotFoundByIdException("Contributor not inserted"));
			 }).orElseThrow(()-> new ContributionPanelNotFindByIdExceptin("contributor not inserted"));
			 
		}).get();
	}

	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteContributor(int userId, int panelId) {
		 String email = SecurityContextHolder.getContext().getAuthentication().getName();

		 return userRepository.findByEmail(email).map(owner -> {
			  return contributionPanelRepository.findById(panelId).map(panel -> {
				if(!blogRepository.existsByUsersAndContributionPanel(owner, panel))
					throw new IllagalAccessRequestException("contributor not inserted");
				return userRepository.findById(userId).map(contributor->{
					panel.getUser().remove(contributor);
					contributionPanelRepository.save(panel);
					
					return ResponseEntity.ok(structure
							.setData(mapToContributeResponse(panel))
							.setMessage("Contributor inserted")
							.setStatuscode(HttpStatus.OK.value()));
				}).orElseThrow(()-> new UserNotFoundByIdException("Contributor not inserted"));
			 }).orElseThrow(()-> new ContributionPanelNotFindByIdExceptin("contributor not inserted"));
			 
		}).get();
	}

	private ContributionPanelResponse mapToContributeResponse(ContributionPanel contributionPanel) {
		return ContributionPanelResponse.builder()
				.users(contributionPanel.getUser())
				.build();
	}
	
}
