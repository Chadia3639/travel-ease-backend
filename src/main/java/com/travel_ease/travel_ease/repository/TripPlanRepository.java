package com.travel_ease.travel_ease.repository;

import com.travel_ease.travel_ease.entity.TripPlan;
import com.travel_ease.travel_ease.entity.TripStatus;
import com.travel_ease.travel_ease.entity.TravelAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TripPlanRepository extends JpaRepository<TripPlan, UUID> {
    List<TripPlan> findByStatusOrderByDepartureDateAsc(TripStatus status);
    List<TripPlan> findByDepartureDateBetweenOrderByDepartureDateAsc(LocalDate start, LocalDate end);
    boolean existsByTravelAgentAndDepartureDateAndStatusNot(TravelAgent travelAgent, LocalDate departureDate, TripStatus status);
    @Query("SELECT tp.status, COUNT(tp) FROM TripPlan tp GROUP BY tp.status")
    List<Object[]> countByStatus();
    @Modifying
    @Query("UPDATE TripPlan tp SET tp.status = com.travel_ease.travel_ease.entity.TripStatus.ABANDONED WHERE tp.travelAgent.id = :id AND tp.departureDate = :date AND tp.status <> com.travel_ease.travel_ease.entity.TripStatus.TRAVELLED")
    int cancelDay(@Param("id") UUID travelAgentId, @Param("date") LocalDate date);
    List<TripPlan> findByTravelAgentAndDepartureDate(TravelAgent travelAgent, LocalDate departureDate);
    long countByDepartureDate(LocalDate date);
    long countByStatus(TripStatus status);
}