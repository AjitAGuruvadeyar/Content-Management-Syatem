package com.example.cms.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.usermodel.ContributionPanel;
import com.example.cms.usermodel.User;
@Repository
public interface ContributionPanelRepository extends JpaRepository<ContributionPanel, Integer>{
  boolean existsByUser(User user);
}
