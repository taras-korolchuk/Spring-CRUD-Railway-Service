package com.example.Lab1.repository;

import com.example.Lab1.entity.CarriageEntity;
import com.example.Lab1.entity.TrainEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarriageRepo extends CrudRepository<CarriageEntity, Long> {
    List<CarriageEntity> findByTrainEntity(TrainEntity trainEntity);
    CarriageEntity findByNumber(Long Number);
    CarriageEntity findByNumberAndTrainEntity(Long Number, TrainEntity trainEntity);

}