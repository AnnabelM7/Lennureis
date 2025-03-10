package com.cgi2025.lennuplaan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Flight flight;

    private String seatNumber;
    private String status;
    private String seatType;
    private boolean extraLegRoom;

    public Seat(Flight flight, String seatNumber, String status, String seatType, boolean extraLegRoom) {
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.status = status;
        this.seatType = seatType;
        this.extraLegRoom = extraLegRoom;
    }

    public Seat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public boolean isExtraLegRoom() {
        return extraLegRoom;
    }

    public void setExtraLegRoom(boolean extraLegRoom) {
        this.extraLegRoom = extraLegRoom;
    }
}