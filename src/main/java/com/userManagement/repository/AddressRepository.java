package com.userManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userManagement.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {}

