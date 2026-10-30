package com.travel_ease.travel_ease.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "agency_branch")
public class AgencyBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int branchNumber;
    private String name;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public int getBranchNumber() { return branchNumber; }
    public void setBranchNumber(int branchNumber) { this.branchNumber = branchNumber; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}