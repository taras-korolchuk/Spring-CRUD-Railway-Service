package com.example.Lab1.service;

import com.example.Lab1.entity.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImportService {

    private final int CARRIAGE_COLUMNS = 4;
    private final int TRAIN_COLUMNS = 2;
    private final int ROUTE_COLUMNS = 2;
    private final int ROUTE_STATION_COLUMNS=3;
    private  final int TRIP_COLUMNS=4;

    @Autowired
    private TrainService trainService;

    @Autowired
    private TripService tripService;

    @Autowired
    private CarriageService carriageService;

    @Autowired
    private RouteStationService routeStationService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

    private String checkEmpty(MultipartFile reapExcelDataFile){
        if (reapExcelDataFile.isEmpty()){
            return "Файл порожній!";
        }
        return null;
    }
    private String checkCells(int numberOfCells, int needed){
        if (numberOfCells != needed){
            return "Неправильна кількість колонок!";
        }
        return null;
    }

    public String importTrain(MultipartFile reapExcelDataFile) throws IOException {

        if (checkEmpty(reapExcelDataFile) != null){
            return checkEmpty(reapExcelDataFile);
        }

        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        int numberOfCells = worksheet.getRow(0).getPhysicalNumberOfCells();
        if (checkCells(numberOfCells, TRAIN_COLUMNS) != null){
            return checkCells(numberOfCells, TRAIN_COLUMNS);
        }

        for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            TrainEntity trainEntity = new TrainEntity();

            XSSFRow row = worksheet.getRow(i);

            trainEntity.setTrainNumber((long)row.getCell(1).getNumericCellValue());

            String check = trainService.createTrainCheck(trainEntity);
            if (check != null){
                return check;
            } else {
                trainService.getTrainRepo().save(trainEntity);
            }

        }

        return null;
    }
    public String importRoute(MultipartFile reapExcelDataFile) throws IOException {

        if (checkEmpty(reapExcelDataFile) != null){
            return checkEmpty(reapExcelDataFile);
        }

        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        int numberOfCells = worksheet.getRow(0).getPhysicalNumberOfCells();
        if (checkCells(numberOfCells, ROUTE_COLUMNS) != null){
            return checkCells(numberOfCells, ROUTE_COLUMNS);
        }

        for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            RouteEntity routeEntity = new RouteEntity();

            XSSFRow row = worksheet.getRow(i);

            routeEntity.setName(row.getCell(1).getStringCellValue());

            String check = routeService.createRouteCheck(routeEntity);
            if (check != null){
                return check;
            } else {
                routeService.getRouteRepo().save(routeEntity);
            }

        }

        return null;
    }

    public String importRouteStation(MultipartFile reapExcelDataFile) throws IOException {

        if (checkEmpty(reapExcelDataFile) != null){
            return checkEmpty(reapExcelDataFile);
        }

        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        int numberOfCells = worksheet.getRow(0).getPhysicalNumberOfCells();
        if (checkCells(numberOfCells, ROUTE_STATION_COLUMNS) != null){
            return checkCells(numberOfCells, ROUTE_STATION_COLUMNS);
        }

        for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            RouteStationEntity routeStationEntity = new RouteStationEntity();

            XSSFRow row = worksheet.getRow(i);
            RouteEntity routeEntity = routeService.getRouteRepo().findByName(row.getCell(1).getStringCellValue());
            if (routeEntity== null)
                return "Невідомий маршрут";
            routeStationEntity.setRouteEntity(routeEntity);
            StationEntity stationEntity = stationService.getStationRepo().findByName(row.getCell(2).getStringCellValue());
                if (stationEntity== null)
                    return "Невідома станція";
            routeStationEntity.setStationEntity(stationEntity);

            String check = routeStationService.createRouteStationCheck(routeStationEntity);
            if (check != null){
                return check;
            } else {
                routeStationService.getRouteStationRepo().save(routeStationEntity);
            }

        }

        return null;
    }

    public String importCarriage(MultipartFile reapExcelDataFile) throws IOException {

        if (checkEmpty(reapExcelDataFile) != null){
            return checkEmpty(reapExcelDataFile);
        }

        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        int numberOfCells = worksheet.getRow(0).getPhysicalNumberOfCells();
        if (checkCells(numberOfCells, CARRIAGE_COLUMNS) != null){
            return checkCells(numberOfCells, CARRIAGE_COLUMNS);
        }

        for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            CarriageEntity carriageEntity = new CarriageEntity();

            XSSFRow row = worksheet.getRow(i);

            TrainEntity trainEntity = trainService.getTrainRepo().findTrainEntityByTrainNumber((long)row.getCell(3).getNumericCellValue());
            if(trainEntity==null)
                return "Невірний номер поїзда";
            carriageEntity.setNumber((long)(row.getCell(1).getNumericCellValue()));
            carriageEntity.setTrainEntity(trainEntity);
            carriageEntity.setSeatNumber((long)(row.getCell(2).getNumericCellValue()));

            String checkHall = carriageService.createCarriageCheck(carriageEntity);
            if (checkHall != null){
                return checkHall;
            } else {
                carriageService.getCarriageRepo().save(carriageEntity);
            }

        }

        return null;
    }


    public String importTrip(MultipartFile reapExcelDataFile) throws IOException {
        if (checkEmpty(reapExcelDataFile) != null){
            return checkEmpty(reapExcelDataFile);
        }

        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        int numberOfCells = worksheet.getRow(0).getPhysicalNumberOfCells();
        if (checkCells(numberOfCells, TRIP_COLUMNS) != null){
            return checkCells(numberOfCells, TRIP_COLUMNS);
        }

        for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            TripEntity tripEntity = new TripEntity();

            XSSFRow row = worksheet.getRow(i);

            TrainEntity trainEntity = trainService.getTrainRepo().findTrainEntityByTrainNumber((long)row.getCell(3).getNumericCellValue());
            if(trainEntity==null)
                return "Невірний номер поїзда";
            RouteEntity routeEntity = routeService.getRouteRepo().findByName(row.getCell(2).getStringCellValue());
            if(routeEntity==null)
                return "Невірна назва маршруту";
            tripEntity.setTrainEntity(trainEntity);
            tripEntity.setRouteEntity(routeEntity);
            tripEntity.setDepartureTime(row.getCell(1).getStringCellValue());

            String checkTrain = tripService.createTripCheck(tripEntity);
            if (checkTrain != null){
                return checkTrain;
            } else {
                tripService.getTripRepo().save(tripEntity);
            }

        }

        return null;
    }
}
