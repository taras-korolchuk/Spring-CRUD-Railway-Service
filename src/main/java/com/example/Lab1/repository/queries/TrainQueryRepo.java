package com.example.Lab1.repository.queries;


import com.example.Lab1.entity.CarriageEntity;
import com.example.Lab1.entity.TrainEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainQueryRepo extends CrudRepository<TrainEntity, Long> {
    @Query(value = "Select distinct train_number " +
            "from train_entity " +
            "join carriage_entity on carriage_entity.train_entity_train_id = train_entity.train_id " +
            "where carriage_entity.seat_number in (" +
                "select seat_number " +
                "from carriage_entity " +
                "where seat_number>?1)",
            nativeQuery = true)
    List<String> getQuery3(Long seatNumber);

    @Query(value="Select distinct train_entity.train_number\n " +
            "from train_entity\n " +
            "join trip_entity on train_entity.train_id=trip_entity.train_entity_train_id\n " +
            "where trip_entity.route_entity_id in(\n " +
            "Select trip_entity.route_entity_id\n " +
            "from trip_entity\n " +
            "join train_entity on train_entity.train_id=trip_entity.train_entity_train_id\n " +
            "where train_entity.train_id in\n " +
            "(Select train_entity.train_id\n " +
            "from train_entity\n " +
            "join carriage_entity on carriage_entity.train_entity_train_id=train_entity.train_id\n " +
            "where carriage_entity.seat_number in \n " +
            "(\n " +
            "Select carriage_entity.seat_number\n " +
            " from carriage_entity\n " +
            " where seat_number>?1)\n " +
            " )\n " +
            ") ;",
            nativeQuery = true)
    List<String> getQuery8(Long count);
}
