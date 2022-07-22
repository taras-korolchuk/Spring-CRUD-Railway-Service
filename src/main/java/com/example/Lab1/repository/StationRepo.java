package com.example.Lab1.repository;

import com.example.Lab1.entity.StationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepo extends CrudRepository<StationEntity, Long> {
    StationEntity findByName(String name);
}
