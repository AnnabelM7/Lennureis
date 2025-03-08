package com.cgi2025.lennuplaan;

import com.cgi2025.lennuplaan.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Test
    public void testGetFlightsFromAPI() {
        String departureAirportCode = "HEL";
        String arrivalAirportCode = "OUL";
        String departureDate = "2025-05-20";
        String numberOfAdults = "1";
        String numberOfChildrens = "0";
        String numberOfInfants = "0";
        String cabinClass = "Economy";
        String currency = "USD";

        String response = flightService.getFlightsFromAPI(departureAirportCode, arrivalAirportCode, departureDate,
                numberOfAdults, numberOfChildrens, numberOfInfants, cabinClass, currency);

        assertNotNull(response, "Vastus API");

        System.out.println(response);
    }
}
