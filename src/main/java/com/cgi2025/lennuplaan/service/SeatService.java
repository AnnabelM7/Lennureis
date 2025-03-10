package com.cgi2025.lennuplaan.service;

import com.cgi2025.lennuplaan.model.Seat;
import com.cgi2025.lennuplaan.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getSeatsByFlight(int flightId) {
        return seatRepository.findAllByFlightId(flightId);
    }

    public Optional<Seat> getSeatById(int seatId) {
        return seatRepository.findById(seatId);
    }

    public Seat addSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public Seat updateSeatStatus(int seatId, String status) {
        Optional<Seat> seatOpt = seatRepository.findById(seatId);
        if (seatOpt.isPresent()) {
            Seat seat = seatOpt.get();
            seat.setStatus(status);
            return seatRepository.save(seat);
        } else {
            return null;
        }
    }

    public void setSeatsAsSelected(int flightId) {
        List<Seat> seats = seatRepository.findByFlightId(flightId);

        Random rand = new Random();

        for (Seat seat : seats) {
            if (rand.nextInt(100) < 20) {
                seat.setStatus("Valitud");
            } else {
                seat.setStatus("Saadaval");
            }
        }

        seatRepository.saveAll(seats);
    }

    public List<Seat> getSeats(int flightId) {
        return seatRepository.findByFlightId(flightId);
    }

}
