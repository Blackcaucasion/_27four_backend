package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Planets;
import com.za.co._27four.assignment.luntu.rapisotory.PlanetsRepository;
import com.za.co._27four.assignment.luntu.utility.ImportsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PlanetsServiceImpl implements PlanetsService {
    private static final Logger LOG = Logger.getLogger(PlanetsServiceImpl.class.getName());

    @Autowired
    PlanetsRepository planetsRepository;
    @Override
    public void savePlanets(MultipartFile file) {
        try {
            LOG.log(Level.INFO, "Importing planets data from csv to embedded db");
            List<Planets> planets = ImportsUtil.excelToPlanetList(file.getInputStream());
            planetsRepository.saveAll(planets);
        } catch (IOException ex) {
            LOG.log(Level.INFO, "Error importing planets data from csv to embedded db");
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }

    @Override
    public List<Planets> findAll() {
        return planetsRepository.findAll();
    }

}