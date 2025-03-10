package com.cgi2025.lennuplaan;

import com.cgi2025.lennuplaan.model.Flight;
import com.cgi2025.lennuplaan.model.Seat;
import com.cgi2025.lennuplaan.repository.FlightRepository;
import com.cgi2025.lennuplaan.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public void run(String... args) {
        //Et midagi ei oleks enne dbs ees
        seatRepository.deleteAll();
        flightRepository.deleteAll();

        // AI tehtud andmed

        List<Flight> flights = Arrays.asList(
                new Flight(0, "Tartu", "Helsinki", 80, java.sql.Timestamp.valueOf("2025-07-04 21:00:00"), "1h 15m", "Finnair", new ArrayList<>()),
                new Flight(0, "Tallinn", "Stockholm", 95, java.sql.Timestamp.valueOf("2025-07-05 10:30:00"), "1h 20m", "Nordica", new ArrayList<>()),
                new Flight(0, "Riga", "Berlin", 120, java.sql.Timestamp.valueOf("2025-07-06 14:00:00"), "2h 10m", "AirBaltic", new ArrayList<>()),
                new Flight(0, "London", "Paris", 150, java.sql.Timestamp.valueOf("2025-07-07 08:45:00"), "1h 30m", "British Airways", new ArrayList<>()),
                new Flight(0, "New York", "Los Angeles", 300, java.sql.Timestamp.valueOf("2025-07-08 12:00:00"), "5h 45m", "Delta", new ArrayList<>()),
                new Flight(0, "Sydney", "Melbourne", 90, java.sql.Timestamp.valueOf("2025-07-09 16:15:00"), "1h 25m", "Qantas", new ArrayList<>()),
                new Flight(0, "Tokyo", "Seoul", 110, java.sql.Timestamp.valueOf("2025-07-10 18:30:00"), "2h 05m", "Korean Air", new ArrayList<>())
        );

        flights = flightRepository.saveAll(flights);

        Random rand = new Random();
        String[] seatTypes = {"Akna all", "Keskel", "Ääres"};

        for (Flight flight : flights) {
            List<Seat> seats = new ArrayList<>();
            for (int row = 1; row <= 7; row++) {
                for (char col = 'A'; col <= 'F'; col++) {
                    String seatNumber = col + String.valueOf(row);
                    String status = rand.nextInt(100) < 20 ? "Broneeritud" : "Saadaval";
                    boolean extraLegRoom = rand.nextInt(100) < 10;
                    String seatType = seatTypes[(col - 'A') % seatTypes.length];

                    seats.add(new Seat(flight, seatNumber, status, seatType, extraLegRoom));
                }
            }
            seatRepository.saveAll(seats);
        }
    }
}
