package com.example.Lab1.model;

import com.example.Lab1.entity.RouteEntity;
import com.example.Lab1.entity.TrainEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Trip {
    @Min(1)
    private Long id;
    @NotNull
    @NotEmpty
    private TrainEntity trainEntity;
    @NotNull
    @NotEmpty
    private RouteEntity routeEntity;
    @NotNull
    @NotEmpty
    private String departureTime;

    public Trip(){}

    public Trip(TrainEntity trainEntity, RouteEntity routeEntity, String departureTime) {
        this.trainEntity = trainEntity;
        this.routeEntity = routeEntity;
        this.departureTime = departureTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainEntity getTrainEntity() {
        return trainEntity;
    }

    public void setTrainEntity(TrainEntity trainEntity) {
        this.trainEntity = trainEntity;
    }

    public RouteEntity getRouteEntity() {
        return routeEntity;
    }

    public void setRouteEntity(RouteEntity routeEntity) {
        this.routeEntity = routeEntity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}
