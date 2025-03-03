package com.cgi2025.lennuplaan.controller;

import com.cgi2025.lennuplaan.model.Flight;
import com.cgi2025.lennuplaan.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

        private final FlightService flightService;

        @Autowired
        public FlightController(FlightService flightService) {
                this.flightService = flightService;
        }

        @GetMapping
        public List<Flight> getAllFlights() {
                return flightService.getAllFlights();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
                Optional<Flight> flight = flightService.getFlightById(id);
                if (flight.isPresent()) {
                        return new ResponseEntity<>(flight.get(), HttpStatus.OK);
                } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @PostMapping
        public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
                Flight savedFlight = flightService.saveFlight(flight);
                return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
                flightService.deleteFlight(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
