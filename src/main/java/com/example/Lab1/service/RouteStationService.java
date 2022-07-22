package com.example.Lab1.service;

import com.example.Lab1.entity.CarriageEntity;
import com.example.Lab1.entity.RouteStationEntity;
import com.example.Lab1.model.Carriage;
import com.example.Lab1.model.RouteStation;
import com.example.Lab1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteStationService {
    @Autowired
    StationRepo stationRepo;
    @Autowired
   public RouteRepo routeRepo;
    @Autowired
    RouteStationRepo routeStationRepo;

    public StationRepo getStationRepo() {
        return stationRepo;
    }

    public RouteRepo getRouteRepo() {
        return routeRepo;
    }

    public RouteStationRepo getRouteStationRepo() {
        return routeStationRepo;
    }

    public String createRouteStationCheck(RouteStation routeStation){
        if(routeStationRepo.findByStationEntityAndRouteEntity(routeStation.getStationEntity(), routeStation.getRouteEntity())!=null)
            return "Маршрут вже містить таку станцію";
        return null;
    }
    public String existenceRouteStationCheck(RouteStation routeStation) {
        RouteStationEntity routeStationEntity = routeStationRepo.findById(routeStation.getId()).get();
        RouteStationEntity returnEntity= new RouteStationEntity(routeStation.getStationEntity(), routeStation.getRouteEntity());

        if ( routeStationEntity.getStationEntity().equals(returnEntity.getStationEntity())){
            if(routeStationEntity.getRouteEntity().equals(returnEntity.getRouteEntity()))
                return "Змініть хоч щось";
            if(routeStationRepo.findByStationEntityAndRouteEntity(returnEntity.getStationEntity(), returnEntity.getRouteEntity())!=null)
                return "Маршрут вже містить таку станцію";
        }
        if(routeStationEntity.getStationEntity().equals(returnEntity.getStationEntity())){
            if(routeStationRepo.findByStationEntityAndRouteEntity(returnEntity.getStationEntity(), returnEntity.getRouteEntity())!=null)
                return "Маршрут вже містить таку станцію";
        }
        return null;
    }
}