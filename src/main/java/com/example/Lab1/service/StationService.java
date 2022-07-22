package com.example.Lab1.service;


import com.example.Lab1.entity.StationEntity;
import com.example.Lab1.model.Station;
import com.example.Lab1.repository.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {
    @Autowired
    private StationRepo stationRepo;

//    @Autowired
//    private MovieRepo movieRepo;
//
//    @Autowired
//    private MovieShowRepo movieShowRepo;
//
    public StationRepo getStationRepo() {
        return stationRepo;
    }
//
//    public MovieRepo getMovieRepo() {
//        return movieRepo;
//    }
//
//    public MovieShowRepo getMovieShowRepo() {
//        return movieShowRepo;
//    }

    public String createStationCheck(Station station) {
        if (stationRepo.findByName(station.getName()) != null) {
            return "Cтанція \"" + station.getName() + "\" вже існує";
        }
        return null;
    }

    public String existenceStationCheck(Station station) {
        StationEntity stationEntity = stationRepo.findById(station.getId()).get();
        StationEntity returnEntity = new StationEntity(station.getName());

        if (stationEntity.getName().equals(returnEntity.getName())) {
            return "Назва збігається зі старою";
        }

        if (stationRepo.findByName(station.getName()) != null) {
            return "Така станція вже існує";
        }
        return null;
    }
}
