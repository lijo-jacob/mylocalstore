package com.lijojacob.mls.siteconfiguration.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lijojacob.mls.common.repository.MlsRepository;
import com.lijojacob.mls.siteconfiguration.entity.SiteConfiguration;

@RepositoryRestResource
public interface SiteConfigurationRepository extends MlsRepository<SiteConfiguration, String> {

}
