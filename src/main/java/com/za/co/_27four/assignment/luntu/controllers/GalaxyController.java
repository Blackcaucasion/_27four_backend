package com.za.co._27four.assignment.luntu.controllers;

import com.za.co._27four.assignment.luntu.entity.RouteResponse;
import com.za.co._27four.assignment.luntu.services.PathService;
import com.za.co._27four.assignment.luntu.services.PlanetsService;
import com.za.co._27four.assignment.luntu.services.RoutesService;
import com.za.co._27four.assignment.luntu.services.TrafficService;
import com.za.co._27four.assignment.luntu.utility.ImportsUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/***
 * A rest controller for all galaxy related operations
 */
@RestController
@RequestMapping("/api/v1")

public class GalaxyController {
    @Autowired
    PlanetsService planetsService;
    @Autowired
    RoutesService routesService;
    @Autowired
    PathService pathService;
    @Autowired
    TrafficService trafficService;

    @Operation(summary = "uploads a csv file to import planets data into an embedded database", description = "Returns response status OK for success")
    @RequestMapping(path = "/planets", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
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

    @Operation(summary = "uploads a csv file to import routes data into an embedded database", description = "Returns response OK for success")
    @RequestMapping(path = "/routes", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadRoutes(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ImportsUtil.hasExcelFormat(file)) {
            try {
                routesService.saveRoutes(file);
                message = "The Routes file is uploaded: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception exp) {
                message = "The Routes file is not upload: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @Operation(summary = "uploads a csv file to import traffic data into an embedded database", description = "Returns response status OK for Success")
    @RequestMapping(path = "/traffic", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadTraffic(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ImportsUtil.hasExcelFormat(file)) {
            try {
                trafficService.saveTraffic(file);
                message = "The Traffic file is uploaded: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception exp) {
                message = "The Traffic file is not upload: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping("/routes/shortest")
    @CrossOrigin(origins = "*")
    @Operation(summary = "Get the shortest path from source to destination", description = "Returns response a Route response object that depict's the path from source to destinantion")
    @ApiResponse(description = "Successfully retrieved")
    public ResponseEntity<RouteResponse> getShortestRoute(@RequestParam String source, @RequestParam String destination) {
        return ResponseEntity.ok(pathService.findShortestPath(source, destination));
    }

}
