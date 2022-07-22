package com.example.Lab1.repository;


import com.example.Lab1.entity.RouteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepo extends CrudRepository<RouteEntity, Long> {
    RouteEntity findByName(String name);
}
