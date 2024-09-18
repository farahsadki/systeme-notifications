package com.xyz.product.delivery.notification.api.repository;

import com.xyz.product.delivery.notification.api.model.common.Address;
import com.xyz.product.delivery.notification.api.model.common.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String>
{
}
