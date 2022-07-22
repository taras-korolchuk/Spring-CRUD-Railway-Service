package com.example.Lab1.service;


import com.example.Lab1.entity.TrainEntity;
import com.example.Lab1.model.Train;
import com.example.Lab1.repository.TrainRepo;
import com.example.Lab1.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainService {
    @Autowired
    private TrainRepo trainRepo;

    //    @Autowired
//    private MovieRepo movieRepo;
//
    @Autowired
    private TripRepo tripRepo;
//
    public TrainRepo getTrainRepo() {
        return trainRepo;
    }
    public TripRepo getTripRepo(){return tripRepo;}
//
//    public MovieRepo getMovieRepo() {
//        return movieRepo;
//    }
//
//    public MovieShowRepo getMovieShowRepo() {
//        return movieShowRepo;
//    }

    public String createTrainCheck(Train train) {
        if (trainRepo.findTrainEntityByTrainNumber(train.getTrainNumber()) != null) {
            return "Поїзд №" + train.getTrainNumber() + " вже існує";
        }
        return null;
    }

    public String existenceTrainCheck(Train train) {
        TrainEntity trainEntity = trainRepo.findById(train.getTrainId()).get();
        TrainEntity returnEntity = new TrainEntity(train.getTrainNumber());

        if (trainEntity.getTrainNumber().equals(returnEntity.getTrainNumber())) {
            return "Номер збігається зі старим";
        }

        if (trainRepo.findTrainEntityByTrainNumber(train.getTrainNumber()) != null) {
            return "Такий номер вже існує";
        }
        return null;
    }
}