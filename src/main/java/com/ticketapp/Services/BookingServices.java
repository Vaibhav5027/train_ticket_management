package com.ticketapp.Services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface BookingServices {
ResponseEntity<?> bookTicket(Map<String,Object> requestMap);
ResponseEntity<?> cancelTicket(Map<String,Object> requestMap);
ResponseEntity<?> downLoadTicket(Map<String,Object> requestMap);
}
