package com.cgi2025.lennuplaan.controller;

import com.cgi2025.lennuplaan.model.Flight;
import com.cgi2025.lennuplaan.model.Seat;
import com.cgi2025.lennuplaan.service.FlightService;
import com.cgi2025.lennuplaan.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    private SeatService seatService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{flightId}/seats")
    public ResponseEntity<List<Seat>> getSeats(@PathVariable int flightId) {
        seatService.setSeatsAsSelected(flightId);
        List<Seat> seats = seatService.getSeats(flightId);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/flights/{id}")
    public Flight getFlightWithSeats(@PathVariable int id) {
        return flightService.getFlightWithSeats(id);
    }

    // Lennud andmebaasist
    @GetMapping("/from-db")
    public List<Flight> getFlightsFromDb() {
        return flightService.getFlightsFromDb();
    }

    // Lennud API-st
    @GetMapping("/from-api")
    public List<Flight> getFlightsFromApi(
            @RequestParam("departure") String departure,
            @RequestParam("date") String date) {
        return flightService.getFlightsFromApi(departure, date);
    }

    @PostMapping
    public Flight saveFlight(@RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id).orElseThrow(() -> new RuntimeException("Lennu ei leitud"));
    }
}
