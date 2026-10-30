package com.travel_ease.travel_ease.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "travel_agent")
public class TravelAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "agency_branch_id")
    private AgencyBranch agencyBranch;

    @ManyToMany
    @JoinTable(name = "travel_agent_travel_interest",
            joinColumns = @JoinColumn(name = "travel_agent_id"),
            inverseJoinColumns = @JoinColumn(name = "travel_interest_id"))
    private List<TravelInterest> travelInterests = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public AgencyBranch getAgencyBranch() { return agencyBranch; }
    public void setAgencyBranch(AgencyBranch agencyBranch) { this.agencyBranch = agencyBranch; }
    public List<TravelInterest> getTravelInterests() { return travelInterests; }
    public void setTravelInterests(List<TravelInterest> travelInterests) { this.travelInterests = travelInterests; }
}