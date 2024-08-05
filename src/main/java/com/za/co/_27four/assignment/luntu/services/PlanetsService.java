package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Planets;
import com.za.co._27four.assignment.luntu.entity.Routes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlanetsService {
    void savePlanets(MultipartFile file);
    List<Planets> findAll();
}
