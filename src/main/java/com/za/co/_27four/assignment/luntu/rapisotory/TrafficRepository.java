package com.za.co._27four.assignment.luntu.rapisotory;

import com.za.co._27four.assignment.luntu.entity.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {
    // Find traffic delay by origin and destination
    /**
     * Find traffic delay by origin and destination.
     *
     * @param origin The origin planet node.
     * @param destination The destination planet node.
     * @return The Traffic entity matching the origin and destination.
     */
    @Query("SELECT t FROM Traffic t WHERE t.origin = :origin AND t.destination = :destination")
    Traffic findByOriginAndDestination(String origin, String destination);
}