package com.example.cms.userservice;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.ContributionPanelResponse;
import com.example.cms.utility.ResponseStructure;

public interface ContributionPanelService {

	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(int userId, int panelId);

	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteContributor(int userId, int panelId);

	
}
