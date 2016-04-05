package com.lijojacob.mls.userprofile.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lijojacob.mls.common.repository.MlsRepository;
import com.lijojacob.mls.userprofile.entity.UserProfile;
import com.lijojacob.mls.userprofile.entity.UserSession;

@RepositoryRestResource
public interface UserSessionRepository extends
		MlsRepository<UserSession, String> {
	
	public UserSession findByUserProfile(UserProfile userProfile);

}
