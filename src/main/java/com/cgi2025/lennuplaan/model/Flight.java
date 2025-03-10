package com.cgi2025.lennuplaan.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String destination;
    private String departure;
    private double price;
    private Date date;
    private String duration;
    private String airline;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    public Flight(int id, String destination, String departure, double price, Date date, String duration, String airline, List<Seat> seats) {
        this.id = id;
        this.destination = destination;
        this.departure = departure;
        this.price = price;
        this.date = date;
        this.duration = duration;
        this.airline = airline;
        this.seats = seats;
    }

    public Flight() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String departureAirport) {
        this.destination = departureAirport;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String arrivalAirport) {
        this.departure = arrivalAirport;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date departureDate) {
        this.date = departureDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String flightTime) {
        this.duration = flightTime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", departure='" + departure + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", duration='" + duration + '\'' +
                ", airline='" + airline + '\'' +
                ", seats=" + seats +
                '}';
    }
}
