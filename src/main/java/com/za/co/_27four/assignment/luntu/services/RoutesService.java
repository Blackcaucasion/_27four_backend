package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Routes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoutesService {
    /***
     * uploads route data from a csv file.
     * @param file
     */
    void saveRoutes(MultipartFile file);

    /***
     *
     * @return a list of routes
     */
    List<Routes> findAll();
}
