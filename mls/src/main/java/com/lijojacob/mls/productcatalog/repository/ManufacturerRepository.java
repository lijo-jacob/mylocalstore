package com.lijojacob.mls.productcatalog.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lijojacob.mls.common.repository.MlsRepository;
import com.lijojacob.mls.productcatalog.entity.Manufacturer;

@RepositoryRestResource
public interface ManufacturerRepository extends MlsRepository<Manufacturer, String> {

}
