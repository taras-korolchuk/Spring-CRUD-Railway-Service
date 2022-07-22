package com.example.Lab1.service;
import com.example.Lab1.entity.RouteEntity;
import com.example.Lab1.model.Route;
import com.example.Lab1.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    @Autowired
    private RouteRepo routeRepo;

    //    @Autowired
//    private MovieRepo movieRepo;
//
//    @Autowired
//    private MovieShowRepo movieShowRepo;
//
    public RouteRepo getRouteRepo() {
        return routeRepo;
    }
//
//    public MovieRepo getMovieRepo() {
//        return movieRepo;
//    }
//
//    public MovieShowRepo getMovieShowRepo() {
//        return movieShowRepo;
//    }

    public String createRouteCheck(Route route) {
        if (routeRepo.findByName(route.getName()) != null) {
            return "Маршрут \"" + route.getName() + "\" вже існує";
        }
        return null;
    }

    public String existenceRouteCheck(Route route) {
        RouteEntity routeEntity = routeRepo.findById(route.getId()).get();
        RouteEntity returnEntity = new RouteEntity(route.getName());

        if (routeEntity.getName().equals(returnEntity.getName())) {
            return "Назва збігається зі старою";
        }

        if (routeRepo.findByName(route.getName()) != null) {
            return "Такий маршрут вже існує";
        }
        return null;
    }
}
