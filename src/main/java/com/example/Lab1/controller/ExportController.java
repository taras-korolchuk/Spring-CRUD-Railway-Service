package com.example.Lab1.controller;

import com.example.Lab1.entity.*;
import com.example.Lab1.export.*;
import com.example.Lab1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
    @Controller
    public class ExportController {
        @Autowired
        private TrainService trainService;

        @Autowired
        private RouteService routeService;

        @Autowired
        private RouteStationService routeStationService;

        @Autowired
        private CarriageService carriageService;

        @Autowired
        private TripService tripService;

        @RequestMapping("/export/train")
        public void getBrandData(HttpServletResponse response) throws IOException {
            response.setContentType("application/octet-stream");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=TrainsData" + ".xlsx";
            response.setHeader(headerKey, headerValue);
            Iterable<TrainEntity> trainEntities = trainService.getTrainRepo().findAll();
            List<TrainEntity> trainEntityList = new ArrayList<>();
            for (TrainEntity trainEntity : trainEntities){
                trainEntityList.add(trainEntity);
            }
            TrainDataExcelExporter excelExporter = new TrainDataExcelExporter(trainEntityList);
            excelExporter.export(response);
        }

        @RequestMapping("/export/route")
        public void getCategoriesData(HttpServletResponse response) throws IOException{
            response.setContentType("application/octet-stream");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=RoutesData" + ".xlsx";
            response.setHeader(headerKey, headerValue);
            Iterable<RouteEntity> routeEntities = routeService.getRouteRepo().findAll();
            List<RouteEntity> routeEntityList = new ArrayList<>();
            for (RouteEntity routeEntity : routeEntities){
                routeEntityList.add(routeEntity);
            }
            RouteDataExcelExporter excelExporter = new RouteDataExcelExporter(routeEntityList);
            excelExporter.export(response);
        }

        @RequestMapping("/export/station")
        public void getRouteStationData(HttpServletResponse response) throws IOException{
            response.setContentType("application/octet-stream");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=StationsData" + ".xlsx";
            response.setHeader(headerKey, headerValue);
            Iterable<RouteStationEntity> routeStationEntities = routeStationService.getRouteStationRepo().findAll();
            List<RouteStationEntity> routeStationEntityList = new ArrayList<>();
            for (RouteStationEntity routeStationEntity : routeStationEntities){
                routeStationEntityList.add(routeStationEntity);
            }
            RouteStationDataExcelExporter excelExporter = new RouteStationDataExcelExporter(routeStationEntityList);
            excelExporter.export(response);
        }

        @RequestMapping("/export/carriage")
        public void getCarriageData(HttpServletResponse response) throws IOException{
            response.setContentType("application/octet-stream");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=CarriageData" + ".xlsx";
            response.setHeader(headerKey, headerValue);
            Iterable<CarriageEntity> carriageEntities = carriageService.getCarriageRepo().findAll();
            List<CarriageEntity> carriageEntityList = new ArrayList<>();
            for (CarriageEntity trainHallEntity : carriageEntities){
                carriageEntityList.add(trainHallEntity);
            }
            CarriageDataExcelExporter excelExporter = new CarriageDataExcelExporter(carriageEntityList);
            excelExporter.export(response);
        }

        @RequestMapping("/export/trip")
        public void getTripData(HttpServletResponse response) throws IOException{
            response.setContentType("application/octet-stream");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=TripData" + ".xlsx";
            response.setHeader(headerKey, headerValue);
            Iterable<TripEntity> movieEntities = tripService.getTripRepo().findAll();
            List<TripEntity> movieShowEntityList = new ArrayList<>();
            for (TripEntity movieEntity : movieEntities){
                movieShowEntityList.add(movieEntity);
            }
            TripDataExcelExporter excelExporter = new TripDataExcelExporter(movieShowEntityList);
            excelExporter.export(response);
        }
    }

