package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Traffic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrafficService {
    /***
     * uploads traffic data from a csv file.
     * @param file
     */
    void saveTraffic(MultipartFile file);

    /****
     *
     * @return a list of traffic data.
     */
    List<Traffic> findAll();
}
