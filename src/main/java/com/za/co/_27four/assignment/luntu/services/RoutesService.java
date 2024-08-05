package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.Routes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoutesService {
    void saveRoutes(MultipartFile file);
    List<Routes> findAll();
}
