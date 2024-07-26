package com.ticketapp.ServiceImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ticketapp.Repository.TrainRepository;
import com.ticketapp.Services.TrainServices;
import com.ticketapp.constants.TrainConstant;
import com.ticketapp.entities.Train;

@Service
public class TrainServiceImpl implements TrainServices {

	@Autowired
	private TrainRepository trainRepository;
	
    private static final Logger logger=LoggerFactory.getLogger(TrainServiceImpl.class);
	@Override
	public ResponseEntity<?> addTrainDetails(Map<String, Object> requestMap) {
		 DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	    LocalTime departureTime = LocalTime.parse((String)requestMap.get("departureTime"), timeFormatter);
	    LocalTime arrivalTime = LocalTime.parse((String)requestMap.get("arrivalTime"), timeFormatter);
		long travelDuration = arrivalTime.getHour()-departureTime.getHour();
		double calculateFair = calculateFair(travelDuration);
	  
	    Train trainDTO = getTrainDTO(requestMap);
		 trainDTO.setTravelDuration(travelDuration);
		 trainDTO.setFair(calculateFair);
		 
		 try {
			 Train save = this.trainRepository.save(trainDTO);
			 if(save.getId()>0) {
				 return  ResponseEntity.ok("{ \"message\": \"" + "train registered succesfully" + "\" }");
			 }
			 }
		 catch(Exception e) {
			logger.error(e.getMessage());
		 }
		
		return null;
	}

	@Override
	public ResponseEntity<?> updateTrainDetails(Map<String, Object> requestMap) {
		return null;
	}

	@Override
	public ResponseEntity<?> searchTrain(Map<String, Object> requestMap) {
		String source = (String)requestMap.get("source");
		String destination = (String)requestMap.get("destination");
	    try {
			List<Train> bySourceAndDestination = trainRepository.findBySourceAndDestination(source, destination);
			 return  ResponseEntity.ok(bySourceAndDestination);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ResponseEntity.ok("{ \"error\": \"" + "no result found" + "\" }");
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
		List<Train> allTrains=new ArrayList<>();
		try {
			allTrains=trainRepository.findAll();
		}catch (Exception e) {
			logger.error(TrainConstant.GenericError);
		}
		return ResponseEntity.ok(allTrains);
	}
	
	public Train getTrainDTO(Map<String, Object> requestMap) {
		Train train =new Train();
		train.setName((String) requestMap.get("trainName")) ;
		train.setSourceStation((String)requestMap.get("sourceStation")); ;
		train.setDestinationStation((String)requestMap.get("destinationStation")); ;
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		train.setDepartureTime(LocalTime.parse((String)requestMap.get("arrivalTime"), formatter));  ;
	    train.setArrivalTime(LocalTime.parse((String)requestMap.get("departureTime"), formatter)) ;	
	    String trainNumber = generateTrainNumber((String)requestMap.get("sourceStation"),(String)requestMap.get("destinationStation"));
	    train.setTrain_number(trainNumber);
		return train;
	}
	
	public String generateTrainNumber(String source,String dest ) {
		int max = 999;
        int min = 100;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
       String trainNumber= source.substring(0,3)+dest.substring(0, 3)+rand;
		return trainNumber;
	}
}
