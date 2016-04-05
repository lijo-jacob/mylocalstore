package com.lijojacob.mls.userprofile.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lijojacob.mls.common.repository.MlsRepository;
import com.lijojacob.mls.userprofile.entity.UserProfile;

@RepositoryRestResource
public interface UserProfileRepository extends
		MlsRepository<UserProfile, String> {
	
	@Query(value = "{'login': ?0}")
	public UserProfile findByLogin(String login);

}
