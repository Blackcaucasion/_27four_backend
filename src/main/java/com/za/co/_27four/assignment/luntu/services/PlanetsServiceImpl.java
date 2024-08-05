package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Planets;
import com.za.co._27four.assignment.luntu.rapisotory.PlanetsRepository;
import com.za.co._27four.assignment.luntu.utility.ImportsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PlanetsServiceImpl implements PlanetsService {
    @Autowired
    PlanetsRepository planetsRepository;
    @Override
    public void savePlanets(MultipartFile file) {
        try {
            List<Planets> planets = ImportsUtil.excelToPlanetList(file.getInputStream());
            planetsRepository.saveAll(planets);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }

    @Override
    public List<Planets> findAll() {
        return planetsRepository.findAll();
    }
//   TODO create function get get data

}