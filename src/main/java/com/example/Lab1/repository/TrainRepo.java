package com.example.Lab1.repository;

import com.example.Lab1.entity.TrainEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Repository
public interface TrainRepo extends CrudRepository<TrainEntity, Long> {
    TrainEntity findTrainEntityByTrainNumber(@NotNull @NotEmpty Long trainNumber);
}
