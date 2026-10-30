package com.travel_ease.travel_ease.repository;

import com.travel_ease.travel_ease.entity.Traveler;
import com.travel_ease.travel_ease.entity.TripPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface TravelerRepository extends JpaRepository<Traveler, UUID> {
    List<Traveler> findByNationalityIgnoreCaseOrderByLastNameAsc(String nationality);
    @Query("SELECT DISTINCT t FROM Traveler t JOIN TripPlan tp ON tp.traveler = t WHERE tp.travelAgent.id = :id")
    List<Traveler> findByTravelAgentId(@Param("id") UUID id);
}