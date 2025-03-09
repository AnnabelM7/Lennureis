package com.cgi2025.lennuplaan.controller;

import com.cgi2025.lennuplaan.model.Flight;
import com.cgi2025.lennuplaan.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
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
