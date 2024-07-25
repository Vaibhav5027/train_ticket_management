package com.ticketapp.Services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface TrainServices {
  ResponseEntity<?> addTrainDetails(Map<String, Object> requestMap);
  ResponseEntity<?> updateTrainDetails(Map<String, Object> requestMap);
  ResponseEntity<?> searchTrain(Map<String, Object> requestMap);
  ResponseEntity<?> scheduleTrain(Map<String, Object> requestMap);
  ResponseEntity<?>getAllScheduledTrains();
  ResponseEntity<?> getAllTrains();  
}
