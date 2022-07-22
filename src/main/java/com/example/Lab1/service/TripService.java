package com.example.Lab1.service;

import com.example.Lab1.entity.RouteStationEntity;
import com.example.Lab1.entity.TripEntity;
import com.example.Lab1.model.RouteStation;
import com.example.Lab1.model.Trip;
import com.example.Lab1.repository.RouteRepo;
import com.example.Lab1.repository.TrainRepo;
import com.example.Lab1.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {
    @Autowired
    TripRepo tripRepo;
    @Autowired
    RouteRepo routeRepo;
    @Autowired
    TrainRepo trainRepo;

    public TripRepo getTripRepo() {
        return tripRepo;
    }

    public RouteRepo getRouteRepo() {
        return routeRepo;
    }

    public TrainRepo getTrainRepo() {
        return trainRepo;
    }

    public String createTripCheck(Trip trip) {
        String date = trip.getDepartureTime();
        if(date.charAt(4)!='-' || date.charAt(7)!='-' || date.length()!=10)
            return "невірна дата";
        for (int i=0; i<10; i++){
            if(i==4 || i==7)
                continue;
            if(date.charAt(i)>57 || date.charAt(i)<48)
                return "невірна дата";
        }
        return null;
    }

    public String existenceTripCheck(Trip trip) {
        System.out.println(trip.getId());
        TripEntity tripEntity = tripRepo.findById(trip.getId()).get();
        TripEntity returnEntity = new TripEntity(trip.getTrainEntity(), trip.getRouteEntity(), trip.getDepartureTime());
        if (tripEntity.getRouteEntity().equals(returnEntity.getRouteEntity())) {
            if (tripEntity.getTrainEntity().equals(returnEntity.getTrainEntity()))
                if (tripEntity.getDepartureTime().equals(returnEntity.getDepartureTime()))
                    return "Змініть хоч щось";
        }
    return null;}
}
