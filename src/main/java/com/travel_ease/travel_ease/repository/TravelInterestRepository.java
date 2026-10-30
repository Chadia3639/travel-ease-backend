package com.travel_ease.travel_ease.repository;

import com.travel_ease.travel_ease.entity.TravelInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface TravelInterestRepository extends JpaRepository<TravelInterest, UUID> {
    @Query("SELECT ti FROM TravelInterest ti WHERE ti.agents IS EMPTY")
    List<TravelInterest> findUnused();
}