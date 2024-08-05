package com.za.co._27four.assignment.luntu.controllers;

import com.za.co._27four.assignment.luntu.entity.RouteResponse;
import com.za.co._27four.assignment.luntu.entity.Routes;
import com.za.co._27four.assignment.luntu.services.PathService;
import com.za.co._27four.assignment.luntu.services.PlanetsService;
import com.za.co._27four.assignment.luntu.services.RoutesService;
import com.za.co._27four.assignment.luntu.services.TrafficService;
import com.za.co._27four.assignment.luntu.utility.ImportsUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api") // fix the base url to include versions

public class GalaxyController {
    @Autowired
    PlanetsService planetsService;
    @Autowired
    RoutesService routesService;
    @Autowired
    PathService pathService;
    @Autowired
    TrafficService trafficService;

    @PostMapping("/planets") //Add API documentation
    public ResponseEntity<?> uploadPlanets(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ImportsUtil.hasExcelFormat(file)) {
            try {
                planetsService.savePlanets(file);
                message = "Planets Excel file is uploaded: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception exp) {
                message = "The Excel file is not upload: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @PostMapping("/routes")
    public ResponseEntity<?> uploadRoutes(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ImportsUtil.hasExcelFormat(file)) {
            try {
                routesService.saveRoutes(file);
                message = "The Excel file is uploaded: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception exp) {
                message = "The Routes file is not upload: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    @GetMapping("/routes")
    public ResponseEntity<?> getStudents() {
        Map<String, Object> respStu = new LinkedHashMap<String, Object>();
        List<Routes> routesList = routesService.findAll();
        if (!routesList.isEmpty()) {
            respStu.put("status", 1);
            respStu.put("data", routesList);
            return new ResponseEntity<>(respStu, HttpStatus.OK);
        } else {
            respStu.clear();
            respStu.put("status", 0);
            respStu.put("message", "Data is not found");
            return new ResponseEntity<>(respStu, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/traffic")
    public ResponseEntity<?> uploadTraffic(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ImportsUtil.hasExcelFormat(file)) {
            try {
                trafficService.saveTraffic(file);
                message = "The Traffic file is uploaded: " + file.getOriginalFilename();  //make custom messages
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception exp) {
                message = "The Excel file is not upload: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    @GetMapping("/routes/shortest")
    @CrossOrigin(origins = "*")
    @Operation(summary = "Get the shortest path from source to destination", description = "Returns response")
    @ApiResponse(description = "Successfully retrieved")
    public ResponseEntity<RouteResponse> getShortestRoute(@RequestParam String source, @RequestParam String destination) {
        return ResponseEntity.ok(pathService.findShortestPath(source, destination));
    }


    // todo do more get reguests for GET,PUT and DELETE
}
