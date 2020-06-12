package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    
}