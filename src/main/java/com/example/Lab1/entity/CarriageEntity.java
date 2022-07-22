package com.example.Lab1.entity;

import com.example.Lab1.model.Carriage;

import javax.persistence.*;

@Entity
public class CarriageEntity extends Carriage {

    public CarriageEntity() {
        super();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @ManyToOne
    @Override
    public TrainEntity getTrainEntity() {
        return super.getTrainEntity();
    }

    @Override
    public void setTrainEntity(TrainEntity trainEntity) {
        super.setTrainEntity(trainEntity);
    }

    @Override
    public void setNumber(Long number) {
        super.setNumber(number);
    }

    @Override
    public void setSeatNumber(Long seatNumber) {
        super.setSeatNumber(seatNumber);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public Long getNumber() {
        return super.getNumber();
    }

    @Column(length = 50, nullable = false)
    @Override
    public Long getSeatNumber() {
        return super.getSeatNumber();
    }

    public CarriageEntity(Long number, Long seatNumber, TrainEntity trainEntity) {
        super(number, seatNumber, trainEntity);
    }
}
