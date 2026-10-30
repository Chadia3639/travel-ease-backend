package com.travel_ease.travel_ease.repository;

import com.travel_ease.travel_ease.entity.TravelAgent;
import com.travel_ease.travel_ease.entity.TravelInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface TravelAgentRepository extends JpaRepository<TravelAgent, UUID> {
    @Query("SELECT ta FROM TravelAgent ta JOIN ta.travelInterests ti WHERE LOWER(ti.name) = LOWER(:name)")
    List<TravelAgent> findByTravelInterestName(@Param("name") String name);
    boolean existsByAgencyBranch(com.travel_ease.travel_ease.entity.AgencyBranch agencyBranch);
}