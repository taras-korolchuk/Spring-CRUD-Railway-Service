package com.example.Lab1.entity;

import com.example.Lab1.model.RouteStation;

import javax.persistence.*;


@Entity
public class RouteStationEntity extends RouteStation {
    @Id
    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return super.getId();
    }

    public RouteStationEntity(StationEntity stationEntity, RouteEntity routeEntity) {
        super(stationEntity, routeEntity);
    }

    public RouteStationEntity() {
        super();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
    @OneToOne
    @Override
    public StationEntity getStationEntity() {
        return super.getStationEntity();
    }

    @Override
    public void setStationEntity(StationEntity stationEntity) {
        super.setStationEntity(stationEntity);
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
}
