package com.ticketapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketapp.entities.Train;

public interface TrainRepository extends JpaRepository<Train, Integer> {

}
