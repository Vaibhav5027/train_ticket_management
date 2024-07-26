package com.ticketapp.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticketapp.Services.TrainServices;

@RestController
@RequestMapping("api/train")
public class TrainController {
	
	@Autowired
	private TrainServices trainService;

	@PostMapping("/addTrain")
	ResponseEntity<?> addTrain(@RequestBody Map<String,Object> requestMap){
		return trainService.addTrainDetails(requestMap);
	} 
	
	@GetMapping("/allTrains")
	ResponseEntity<?> getAllTrains(){
		return trainService.getAllTrains();
	}
	
	@PostMapping("/findTrain")
	ResponseEntity<?> findTrain(@RequestBody Map<String,Object> requestMap){
		return trainService.searchTrain(requestMap);
	}
}
