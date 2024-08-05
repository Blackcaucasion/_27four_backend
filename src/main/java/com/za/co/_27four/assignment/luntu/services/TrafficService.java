package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Traffic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrafficService {
    void saveTraffic(MultipartFile file);
    List<Traffic> findAll();
}
