package com.example.Lab1.model;

import com.example.Lab1.entity.TrainEntity;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Carriage {
    @Min(1)
    private Long id;

    @NotNull
    @NotEmpty
    private Long number;

    @NotNull
    @NotEmpty
    private Long seatNumber;

    @NotNull
    @NotEmpty
    private TrainEntity trainEntity;

    public Carriage(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getId() {
        return id;
    }

    public Long getNumber() {
        return number;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public TrainEntity getTrainEntity() {
        return trainEntity;
    }

    public void setTrainEntity(TrainEntity trainEntity) {
        this.trainEntity = trainEntity;
    }

    public Carriage(Long number, Long seatNumber, TrainEntity trainEntity) {
        this.number = number;
        this.seatNumber = seatNumber;
        this.trainEntity=trainEntity;
    }
}
