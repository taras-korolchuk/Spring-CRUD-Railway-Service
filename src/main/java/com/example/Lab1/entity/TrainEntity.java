package com.example.Lab1.entity;

import com.example.Lab1.model.Train;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Entity
public class TrainEntity extends Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getTrainId() {
        return super.getTrainId();
    }
    public TrainEntity() {
        super();
    }
    public TrainEntity(@NotNull @NotEmpty Long trainNumber) {
        super(trainNumber);
    }

    @Override
    public void setTrainId(Long id) {
        super.setTrainId(id);
    }

    @Column(nullable = false)
    @Override
    public Long getTrainNumber() {
        return super.getTrainNumber();
    }

    @Override
    public void setTrainNumber(Long number) {
        super.setTrainNumber(number);
    }

}
