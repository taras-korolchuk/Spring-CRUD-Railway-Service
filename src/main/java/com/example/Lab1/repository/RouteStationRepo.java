package com.example.Lab1.repository;


import com.example.Lab1.entity.RouteEntity;
import com.example.Lab1.entity.RouteStationEntity;
import com.example.Lab1.entity.StationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteStationRepo extends CrudRepository<RouteStationEntity, Long> {
    List<RouteStationEntity> findByRouteEntity(RouteEntity routeEntity);
    RouteStationEntity findByStationEntity(StationEntity stationEntity);
    RouteStationEntity findByStationEntityAndRouteEntity(StationEntity stationEntity, RouteEntity routeEntity);
    Integer countAllByRouteEntity(RouteEntity routeEntity);
}
