package com.travel_ease.travel_ease.repository;

import com.travel_ease.travel_ease.entity.AgencyBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgencyBranchRepository extends JpaRepository<AgencyBranch, UUID> {
    Optional<AgencyBranch> findByBranchNumber(int branchNumber);
    @Query("SELECT ab FROM AgencyBranch ab WHERE NOT EXISTS (SELECT ta FROM TravelAgent ta WHERE ta.agencyBranch = ab)")
    List<AgencyBranch> findFree();
}