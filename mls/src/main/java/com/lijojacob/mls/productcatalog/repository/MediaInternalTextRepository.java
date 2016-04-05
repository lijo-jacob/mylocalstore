package com.lijojacob.mls.productcatalog.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lijojacob.mls.common.repository.MlsRepository;
import com.lijojacob.mls.productcatalog.entity.MediaInternalText;

@RepositoryRestResource
public interface MediaInternalTextRepository extends MlsRepository<MediaInternalText, String> {

}
