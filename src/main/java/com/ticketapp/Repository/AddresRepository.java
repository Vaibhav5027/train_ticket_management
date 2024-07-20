package com.ticketapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketapp.entities.Address;

public interface AddresRepository extends JpaRepository<Address, Integer> {

}
