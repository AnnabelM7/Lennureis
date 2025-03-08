package com.cgi2025.lennuplaan.service;

import com.cgi2025.lennuplaan.model.Flight;
import com.cgi2025.lennuplaan.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;

    @Value("${flight.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.aviationstack.com/v1/flights";

    @Autowired
    public FlightService(FlightRepository flightRepository, RestTemplate restTemplate) {
        this.flightRepository = flightRepository;
        this.restTemplate = restTemplate;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> getFlights(String departure, String date) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("access_key", apiKey)
                .queryParam("dep_iata", departure)
                .queryParam("flight_date", date)
                .queryParam("limit", 10)
                .toUriString();

        // Teeme päringu API
        ResponseEntity<Flight[]> response = restTemplate.getForEntity(url, Flight[].class);

        return List.of(response.getBody());
    }
}
