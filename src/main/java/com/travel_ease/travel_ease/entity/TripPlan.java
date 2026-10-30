package com.travel_ease.travel_ease.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "trip_plan")
public class TripPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate departureDate;
    @Enumerated(EnumType.STRING)
    private TripStatus status;
    private String destination;
    private double budget;

    @ManyToOne
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;

    @ManyToOne
    @JoinColumn(name = "travel_agent_id")
    private TravelAgent travelAgent;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDate departureDate) { this.departureDate = departureDate; }
    public TripStatus getStatus() { return status; }
    public void setStatus(TripStatus status) { this.status = status; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }
    public Traveler getTraveler() { return traveler; }
    public void setTraveler(Traveler traveler) { this.traveler = traveler; }
    public TravelAgent getTravelAgent() { return travelAgent; }
    public void setTravelAgent(TravelAgent travelAgent) { this.travelAgent = travelAgent; }
}