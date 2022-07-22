package com.example.Lab1.model;

import com.example.Lab1.entity.RouteEntity;
import com.example.Lab1.entity.StationEntity;
import com.example.Lab1.entity.TrainEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RouteStation {
    @Min(1)
    private Long id;
    @NotNull
    @NotEmpty
    private StationEntity stationEntity;
    @NotNull
    @NotEmpty
    private RouteEntity routeEntity;

    public RouteStation(StationEntity stationEntity, RouteEntity routeEntity) {
        this.stationEntity = stationEntity;
        this.routeEntity = routeEntity;
    }
    public RouteStation(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StationEntity getStationEntity() {
        return stationEntity;
    }

    public void setStationEntity(StationEntity stationEntity) {
        this.stationEntity = stationEntity;
    }

    public RouteEntity getRouteEntity() {
        return routeEntity;
    }

    public void setRouteEntity(RouteEntity routeEntity) {
        this.routeEntity = routeEntity;
    }
}
