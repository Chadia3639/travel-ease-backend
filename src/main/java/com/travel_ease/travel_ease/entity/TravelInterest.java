package com.travel_ease.travel_ease.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "travel_interest")
public class TravelInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(mappedBy = "travelInterests")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private java.util.List<TravelAgent> agents;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public java.util.List<TravelAgent> getAgents() { return agents; }
    public void setAgents(java.util.List<TravelAgent> agents) { this.agents = agents; }
}