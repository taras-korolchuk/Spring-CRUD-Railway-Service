package com.example.Lab1.service;

import com.example.Lab1.entity.CarriageEntity;
import com.example.Lab1.entity.RouteEntity;
import com.example.Lab1.model.Carriage;
import com.example.Lab1.model.Route;
import com.example.Lab1.repository.CarriageRepo;
import com.example.Lab1.repository.TrainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarriageService {
    @Autowired
    CarriageRepo carriageRepo;
    @Autowired
    TrainRepo trainRepo;
    @Autowired
    public CarriageRepo getCarriageRepo(){return carriageRepo;}

    public TrainRepo getTrainRepo() {
        return trainRepo;
    }


    public String createCarriageCheck(Carriage carriage){
        if(carriageRepo.findByNumberAndTrainEntity(carriage.getNumber(), carriage.getTrainEntity())!=null)
            return "Вагон з таким номером вже існує";
        if(carriage.getSeatNumber()<10)
            return "Мінімальна кількість сидінь - 10";
        return null;
    }
    public String existenceCarriageCheck(Carriage carriage) {
        CarriageEntity carriageEntity = carriageRepo.findById(carriage.getId()).get();
        CarriageEntity returnEntity = new CarriageEntity(carriage.getNumber(), carriage.getSeatNumber(), carriage.getTrainEntity());

        if ( carriageEntity.getTrainEntity().equals(returnEntity.getTrainEntity())){
               if( carriageEntity.getNumber().equals(returnEntity.getNumber()) &
                carriageEntity.getSeatNumber()==returnEntity.getSeatNumber()) {
                   return "Змініть хоч щось";
               }
            if(carriageRepo.findByNumberAndTrainEntity(carriage.getNumber(), carriage.getTrainEntity())!=null)
                return "В цьому поїзді вже є вагон з таким номером";
//                if(carriageEntity.getNumber()==returnEntity.getNumber())
//                    return "В цьому поїзді вже є вагон з таким номером";
//                if(carriageEntity.getSeatNumber().equals(returnEntity.getSeatNumber()))
//                    return "Кількість місць зібгається зі старою";
//                return null;
        }
        else{
            if(carriageRepo.findByNumberAndTrainEntity(carriage.getNumber(), carriage.getTrainEntity())!=null)
                return "В цьому поїзді вже є вагон з таким номером";
        }


//        if (carriageRepo.findByNumber(carriage.getNumber()) != null) {
//            return "Такий номер вагона вже існує";
//        }
        return null;
    }

}
