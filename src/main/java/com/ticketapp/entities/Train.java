package com.ticketapp.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@JsonIgnore
	private String train_number;
    private String trainName;
    private String sourceStation;
    private String destinationStation;
//  private List<String> stops; 
    private LocalTime  departureTime;
    private LocalTime  arrivalTime;
    private long travelDuration;
    private double fair;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
    public double getFair() {
		return fair;
	}
	public void setFair(double fair) {
		this.fair = fair;
	}
	private double total_distance;

	public double getTotal_distance() {
		return total_distance;
	}
	public void setTotal_distance(double total_distance) {
		this.total_distance = total_distance;
	}
	public String getTrain_number() {
		return train_number;
	}
	public void setTrain_number(String train_number) {
		this.train_number = train_number;
	}
	public String getName() {
		return trainName;
	}
	public void setName(String trainName) {
		this.trainName = trainName;
	}
	public String getSourceStation() {
		return sourceStation;
	}
	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}
	public String getDestinationStation() {
		return destinationStation;
	}
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public long getTravelDuration() {
		return travelDuration;
	}
	public void setTravelDuration(long travelDuration) {
		this.travelDuration = travelDuration;
	}
    
}
