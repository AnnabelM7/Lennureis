package com.cgi2025.lennuplaan.controller;


import com.cgi2025.lennuplaan.model.Seat;
import com.cgi2025.lennuplaan.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/flight/{flightId}")
    public List<Seat> getSeatsByFlight(@PathVariable int flightId) {
        return seatService.getSeatsByFlight(flightId);
    }

    @GetMapping("/{seatId}")
    public Optional<Seat> getSeatById(@PathVariable int seatId) {
        return seatService.getSeatById(seatId);
    }

    @PostMapping
    public Seat addSeat(@RequestBody Seat seat) {
        return seatService.addSeat(seat);
    }

    @PatchMapping("/{seatId}")
    public Seat updateSeatStatus(@PathVariable int seatId, @RequestParam String status) {
        return seatService.updateSeatStatus(seatId, status);
    }
}
