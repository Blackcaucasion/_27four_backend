package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Planets;
import com.za.co._27four.assignment.luntu.entity.Routes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlanetsService {
    /***
     * uploads planets data from a csv file.
     * @param file
     */
    void savePlanets(MultipartFile file);

    /***
     *
     * @return a list of all planets
     */
    List<Planets> findAll();
}
