package com.cgi2025.lennuplaan.model;

import jakarta.persistence.*;

import java.util.Date;

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


    public Flight(String destination, String departure, double price, Date date, String duration, String airline) {
        this.destination = destination;
        this.departure = departure;
        this.price = price;
        this.date = date;
        this.duration = duration;
        this.airline = airline;
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

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", departureAirport='" + destination + '\'' + ", arrivalAirport='" + departure + '\'' + ", price=" + price + ", departureDate=" + date + ", flightTime='" + duration + '\'' + ", airline='" + airline + '\'' + '}';
    }
}
