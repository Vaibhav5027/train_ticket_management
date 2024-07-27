package com.ticketapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketapp.entities.ScheduleTrains;

public interface ScheduleTrainRepo extends JpaRepository<ScheduleTrains, Long> {

}
