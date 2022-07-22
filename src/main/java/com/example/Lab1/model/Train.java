package com.example.Lab1.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Train {
    @Min(1)
    private Long trainId;

    @NotNull
    @NotEmpty
    private Long trainNumber;

    public Train() {
    }

    public Train( @NotNull @NotEmpty Long trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public void setTrainNumber(Long trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Long getTrainId() {
        return trainId;
    }

    public Long getTrainNumber() {
        return trainNumber;
    }
}
