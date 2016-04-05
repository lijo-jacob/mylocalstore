package com.lijojacob.mls.order.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lijojacob.mls.common.repository.MlsRepository;
import com.lijojacob.mls.order.entity.Order;

@RepositoryRestResource
public interface CommerceItemRepository extends MlsRepository<Order, String> {

}
