package com.za.co._27four.assignment.luntu.rapisotory;

import com.za.co._27four.assignment.luntu.entity.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoutesRepository  extends JpaRepository<Routes,Long> {
    // Find a route by origin and destination
    /**
     * Find a route by origin and destination.
     *
     * @param origin The origin planet node.
     * @param destination The destination planet node.
     * @return The Route entity matching the origin and destination.
     */
    @Query("SELECT r FROM Routes r WHERE r.PlanetOrigin = :origin AND r.PlanetDestination = :destination")
    Routes findByOriginAndDestination(@Param("origin") String origin, @Param("destination") String destination);

    // Find all routes originating from a planet
    @Query("SELECT r FROM Routes r WHERE r.PlanetOrigin = :origin")
    List<Routes> findByOrigin(String origin);
}

