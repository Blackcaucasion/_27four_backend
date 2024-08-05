package com.za.co._27four.assignment.luntu.utility;

import com.za.co._27four.assignment.luntu.entity.Planets;
import com.za.co._27four.assignment.luntu.entity.Routes;
import com.za.co._27four.assignment.luntu.entity.Traffic;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportsUtil {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static String PLANET_SHEET = "Planet Names";
    private static String ROUTE_SHEET = "Routes";
    private static String TRAFFIC_SHEET = "Traffic";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Planets> excelToPlanetList(InputStream is) {  // TODO create custom exception
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(PLANET_SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Planets> planetList = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Planets planet = new Planets();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    // TODO handle empty cells
                    switch (cellIdx) {
                        case 0:
                            planet.setPlanetNode(currentCell.getStringCellValue());
                            break;
                        case 1:
                            planet.setPlanetName(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                planetList.add(planet);
            }
            workbook.close();
            return planetList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Routes> excelToRoutesList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(ROUTE_SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Routes> routetList = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Routes route = new Routes();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            route.setRouteId((long) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            route.setPlanetOrigin(currentCell.getStringCellValue());
                            break;
                        case 2:
                            route.setPlanetDestination(currentCell.getStringCellValue());
                            break;
                        case 3:
                            route.setDistance(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                routetList.add(route);
            }
            workbook.close();
            return routetList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    public static List<Traffic> excelToTrafficList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(TRAFFIC_SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Traffic> trafficList = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Traffic traffic = new Traffic();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            traffic.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            traffic.setOrigin(currentCell.getStringCellValue());
                            break;
                        case 2:
                            traffic.setDestination(currentCell.getStringCellValue());
                            break;
                        case 3:
                            traffic.setDelay(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                trafficList.add(traffic);
            }
            workbook.close();
            return trafficList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
