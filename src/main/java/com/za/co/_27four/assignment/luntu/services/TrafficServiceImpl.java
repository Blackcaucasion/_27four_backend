package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Traffic;
import com.za.co._27four.assignment.luntu.rapisotory.TrafficRepository;
import com.za.co._27four.assignment.luntu.utility.ImportsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TrafficServiceImpl implements TrafficService {
    @Autowired
    TrafficRepository trafficRepository;

    @Override
    public void saveTraffic(MultipartFile file) {
        try {
            List<Traffic> traffic = ImportsUtil.excelToTrafficList(file.getInputStream());
            trafficRepository.saveAll(traffic);
        } catch (IOException ex) {
            throw new RuntimeException("Excel data is failed to store: " + ex.getMessage());
        }
    }

    @Override
    public List<Traffic> findAll() {
        return trafficRepository.findAll();
    }
}
