package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Routes;
import com.za.co._27four.assignment.luntu.rapisotory.RoutesRepository;
import com.za.co._27four.assignment.luntu.utility.ImportsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RoutesServiceImpl implements RoutesService {
    private static final Logger LOG = Logger.getLogger(RoutesServiceImpl.class.getName());

    @Autowired
    RoutesRepository routesRepository;

    @Override
    public void saveRoutes(MultipartFile file) {

        try {
            LOG.log(Level.INFO, "Importing routes data from csv to embedded db");
            List<Routes> routes = ImportsUtil.excelToRoutesList(file.getInputStream());
            routesRepository.saveAll(routes);
        } catch (IOException ex) {
            LOG.log(Level.WARNING, "Error importing routes data from csv to embedded db");
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }

    @Override
    public List<Routes> findAll()
    {
        return routesRepository.findAll();
    }
}