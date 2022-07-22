package com.example.Lab1.repository;


import com.example.Lab1.entity.RouteEntity;
import com.example.Lab1.entity.TrainEntity;
import com.example.Lab1.entity.TripEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TripRepo extends CrudRepository<TripEntity, Long> {
    List<TripEntity> findByTrainEntity(TrainEntity trainEntity);
    List<TripEntity> findByRouteEntity(RouteEntity routeEntity);
    TripEntity findByTrainEntityAndRouteEntityAndDepartureTime(TrainEntity trainEntity, RouteEntity routeEntity, LocalDate departureTime);
    int countAllByTrainEntity(TrainEntity trainEntity);
    Integer countAllByRouteEntity(RouteEntity routeEntity);
}
