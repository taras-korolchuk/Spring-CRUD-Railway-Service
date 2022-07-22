package com.example.Lab1.entity;

import com.example.Lab1.model.Trip;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class TripEntity extends Trip {
    public TripEntity() {
    }

    public TripEntity(TrainEntity trainEntity, RouteEntity routeEntity, String departureTime) {
        super(trainEntity, routeEntity, departureTime);
    }
    @Id
    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
    @ManyToOne
    @Override
    public TrainEntity getTrainEntity() {
        return super.getTrainEntity();
    }

    @Override
    public void setTrainEntity(TrainEntity trainEntity) {
        super.setTrainEntity(trainEntity);
    }
    @ManyToOne
    @Override
    public RouteEntity getRouteEntity() {
        return super.getRouteEntity();
    }

    @Override
    public void setRouteEntity(RouteEntity routeEntity) {
        super.setRouteEntity(routeEntity);
    }

    @Override
    public String getDepartureTime() {
        return super.getDepartureTime();
    }

    @Override
    public void setDepartureTime(String departureTime) {
        super.setDepartureTime(departureTime);
    }
}
