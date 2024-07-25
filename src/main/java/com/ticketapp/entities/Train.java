package com.ticketapp.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Train {
	@Id
	private Long train_number;
    private String trainName;
    private String sourceStation;
    private String destinationStation;
//  private List<String> stops; 
    private LocalDateTime  departureTime;
    private LocalDateTime  arrivalTime;
    private String travelDuration;
    private double fair;
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
	public Long getTrain_number() {
		return train_number;
	}
	public void setTrain_number(Long train_number) {
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
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getTravelDuration() {
		return travelDuration;
	}
	public void setTravelDuration(String travelDuration) {
		this.travelDuration = travelDuration;
	}
    
}
