package com.example.Lab1.service;

import com.example.Lab1.entity.RouteEntity;
import com.example.Lab1.entity.TrainEntity;
import com.example.Lab1.model.RouteStation;
import com.example.Lab1.repository.RouteStationRepo;
import com.example.Lab1.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
public class GoogleChartsUtils {

    @Autowired
    private TripRepo tripRepo;
    @Autowired
    private RouteStationRepo routeStationRepo;

    public Map<Long, Integer> getTripTrainsMap(Iterable<TrainEntity> trainEntities) {
        Map<Long, Integer> trainsMap = new TreeMap<>();
        for (TrainEntity trainEntity : trainEntities) {
            trainsMap.put(trainEntity.getTrainNumber(), tripRepo.countAllByTrainEntity(trainEntity));
        }
        return trainsMap;
    }

    public Map<String, Integer> getTripRoutesMap(Iterable<RouteEntity> routeEntities) {
        Map<String, Integer> routesMap = new TreeMap<>();
        for (RouteEntity routeEntity : routeEntities) {
            routesMap.put(routeEntity.getName() + "  ", tripRepo.countAllByRouteEntity(routeEntity));
        }
        return routesMap;
    }

    public Map<String, Integer> getRouteStationByRouteMap(Iterable<RouteEntity> routeEntities){
        Map<String, Integer> routesMap = new TreeMap<>();
        for (RouteEntity routeEntity: routeEntities){
            routesMap.put(routeEntity.getName()+" ", routeStationRepo.countAllByRouteEntity(routeEntity));
        }
        return routesMap;
    }
}
