package com.example.Lab1.repository.queries;

import com.example.Lab1.entity.RouteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteQueryRepo extends CrudRepository<RouteEntity, Long> {
    @Query(value = "Select distinct name " +
            "from route_entity " +
            "where( " +
            "Select count(*) " +
            "from route_station_entity " +
            "where route_entity_id=route_entity.id)>?1",
            nativeQuery = true)
    List<String> getQuery4(Long count);

    @Query(value = "Select distinct route_entity.name " +
            "from route_entity " +
            "join route_station_entity rse on route_entity.id=rse.route_entity_id " +
            "join station_entity on station_entity.id=rse.station_entity_id " +
            "where station_entity.name=?;",
            nativeQuery = true)
    List<String> getQuery5(String station);

    @Query(value= "SELECT DISTINCT route_entity.name FROM route_entity " +
            "join route_station_entity on route_entity.id = route_station_entity.route_entity_id " +
            "join station_entity on station_entity.id=route_station_entity.station_entity_id " +
            "WHERE NOT EXISTS " +
            "(SELECT route_station_entity.station_entity_id " +
            "FROM route_station_entity  " +
            "WHERE route_station_entity.route_entity_id = route_entity.id " +
            "AND route_station_entity.station_entity_id NOT IN " +
            "(SELECT route_station_entity.station_entity_id FROM route_station_entity " +
            " WHERE route_station_entity.route_entity_id = ?1));",
            nativeQuery = true)
    List<String> getQuery6(Long route);

    @Query(value= "SELECT DISTINCT route_entity.name FROM route_entity " +
            "join route_station_entity on route_entity.id = route_station_entity.route_entity_id " +
            "join station_entity on station_entity.id=route_station_entity.station_entity_id " +
            "WHERE NOT EXISTS " +
            "(SELECT route_station_entity.station_entity_id " +
            "FROM route_station_entity  " +
            "WHERE route_station_entity.route_entity_id =?1  " +
            "AND route_station_entity.station_entity_id NOT IN " +
            "(SELECT route_station_entity.station_entity_id FROM route_station_entity " +
            " WHERE route_station_entity.route_entity_id =route_entity.id ));",
            nativeQuery = true)
    List<String> getQuery7(Long route);


}
