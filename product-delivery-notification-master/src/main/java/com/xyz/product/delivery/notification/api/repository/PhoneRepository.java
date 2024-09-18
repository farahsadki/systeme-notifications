package com.xyz.product.delivery.notification.api.repository;

import com.xyz.product.delivery.notification.api.model.common.PhoneNumber;
import com.xyz.product.delivery.notification.api.model.customer.CustomerDetails;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<PhoneNumber, String>
{
}
