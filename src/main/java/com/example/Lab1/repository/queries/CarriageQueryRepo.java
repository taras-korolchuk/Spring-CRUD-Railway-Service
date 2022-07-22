package com.example.Lab1.repository.queries;


import com.example.Lab1.entity.CarriageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarriageQueryRepo extends CrudRepository<CarriageEntity, Long> {
    @Query(value = "Select distinct carriage_entity.number " +
            "from carriage_entity " +
            "join train_entity on carriage_entity.train_entity_train_id = train_entity.train_id " +
            "where train_entity.train_number=?1",
            nativeQuery = true)
    List<String> getQuery1(Long trainNumber);
    @Query(value="Select Max(seat_number) " +
            "from carriage_entity " +
            "join train_entity on carriage_entity.train_entity_train_id=train_entity.train_id " +
            "where train_entity.train_id=?1",
    nativeQuery = true)
    List<String> getQuery2(Long trainId);

}
