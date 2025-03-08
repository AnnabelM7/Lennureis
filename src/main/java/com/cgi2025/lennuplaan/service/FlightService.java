package com.cgi2025.lennuplaan.service;

import com.cgi2025.lennuplaan.model.Flight;
import com.cgi2025.lennuplaan.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;

    @Value("${flight.api.key}")
    private String apiKey;

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

    public String getFlightsFromAPI(String departureAirportCode, String arrivalAirportCode, String departureDate,
                                    String numberOfAdults, String numberOfChildrens, String numberOfInfants,
                                    String cabinClass, String currency) {
        String url = String.format("https://api.flightapi.io/onewaytrip/%s/%s/%s/%s/%s/%s/%s/%s/%s",
                apiKey, departureAirportCode, arrivalAirportCode, departureDate, numberOfAdults,
                numberOfChildrens, numberOfInfants, cabinClass, currency);

        return restTemplate.getForObject(url, String.class);
    }
}
