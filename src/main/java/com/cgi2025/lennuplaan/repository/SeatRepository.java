package com.cgi2025.lennuplaan.repository;

import com.cgi2025.lennuplaan.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findAllByFlightId(int flightId);

    List<Seat> findByFlightId(int flightId);
}