package com.travel_ease.travel_ease.repository;

import com.travel_ease.travel_ease.entity.TripExpense;
import com.travel_ease.travel_ease.entity.TripPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface TripExpenseRepository extends JpaRepository<TripExpense, UUID> {
    boolean existsByTripPlan(TripPlan tripPlan);
    @Query("SELECT te FROM TripExpense te WHERE te.tripPlan.traveler.passportNumber = :passport ORDER BY te.recordedDate DESC")
    List<TripExpense> findByTravelerPassport(@Param("passport") String passport);
}