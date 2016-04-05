package com.lijojacob.mls.siteconfiguration.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lijojacob.mls.common.repository.MlsRepository;
import com.lijojacob.mls.siteconfiguration.entity.CategoryLandingPageConfiguration;

@RepositoryRestResource
public interface CategoryLandingPageConfigurationRepository extends 
		MlsRepository<CategoryLandingPageConfiguration, String> {

}
