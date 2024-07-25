package com.ticketapp.ServiceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ticketapp.Repository.TrainRepository;
import com.ticketapp.Services.TrainServices;
import com.ticketapp.constants.TrainConstant;

@Service
public class TrainServiceImpl implements TrainServices {

	@Autowired
	private TrainRepository trainRepository;

	@Override
	public ResponseEntity<?> addTrainDetails(Map<String, Object> requestMap) {
		 DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	    LocalTime departureTime = LocalTime.parse((String)requestMap.get("departureTime"), timeFormatter);
	    LocalTime arrivalTime = LocalTime.parse((String)requestMap.get("arrivalTime"), timeFormatter);
		long travelDuration = arrivalTime.getHour()-departureTime.getHour();
		return null;
	}

	@Override
	public ResponseEntity<?> updateTrainDetails(Map<String, Object> requestMap) {
		return null;
	}

	@Override
	public ResponseEntity<?> searchTrain(Map<String, Object> requestMap) {
		return null;
	}

	private double calculateFair(double km) {
		return km * TrainConstant.farePerKm;
	}

	//this method for only admin
	@Override
	public ResponseEntity<?> scheduleTrain(Map<String, Object> requestMap) {
		
		return null;
	}

	@Override
	public ResponseEntity<?> getAllScheduledTrains() {
		return null;
	}

	@Override
	public ResponseEntity<?> getAllTrains() {
		return null;
	}
}
