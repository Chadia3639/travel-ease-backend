package com.travel_ease.travel_ease.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "trip_expense")
public class TripExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String expenseName;
    private String note;
    private int amount;
    private LocalDate recordedDate;

    @ManyToOne
    @JoinColumn(name = "trip_plan_id")
    private TripPlan tripPlan;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getExpenseName() { return expenseName; }
    public void setExpenseName(String expenseName) { this.expenseName = expenseName; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public LocalDate getRecordedDate() { return recordedDate; }
    public void setRecordedDate(LocalDate recordedDate) { this.recordedDate = recordedDate; }
    public TripPlan getTripPlan() { return tripPlan; }
    public void setTripPlan(TripPlan tripPlan) { this.tripPlan = tripPlan; }
}