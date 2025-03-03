package com.cgi2025.lennuplaan.repository;

import com.cgi2025.lennuplaan.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


}
