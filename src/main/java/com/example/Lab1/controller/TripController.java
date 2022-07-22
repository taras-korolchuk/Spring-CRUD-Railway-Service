package com.example.Lab1.controller;

import com.example.Lab1.entity.*;

import com.example.Lab1.model.Train;
import com.example.Lab1.model.Trip;
import com.example.Lab1.service.GoogleChartsUtils;
import com.example.Lab1.service.TrainService;
import com.example.Lab1.service.TripService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
public class TripController {
    @Autowired
    private TripService tripService;

    @Autowired
    private GoogleChartsUtils gcu;

    @RequestMapping("/trip")
    public String getTrips(Model model){
        Iterable<TrainEntity> trains = tripService.getTrainRepo().findAll();
        Iterable<RouteEntity> routes = tripService.getRouteRepo().findAll();
        Iterable<TripEntity> trips = tripService.getTripRepo().findAll();

        model.addAttribute("trainsData", gcu.getTripTrainsMap(trains));
        model.addAttribute("routesData", gcu.getTripRoutesMap(routes));
        model.addAttribute("trips", trips);

        return "trips/trip";
    }
    @RequestMapping("/trip1")
    public String getTrips1(Model model){
        var train = tripService.getRouteRepo().findByName("Kharkiv-Kyiv");
        Iterable<TrainEntity> trains = tripService.getTrainRepo().findAll();
        Iterable<RouteEntity> routes = tripService.getRouteRepo().findAll();
        Iterable<TripEntity> trips = tripService.getTripRepo().findByRouteEntity(train);

        model.addAttribute("trainsData", gcu.getTripTrainsMap(trains));
        model.addAttribute("routesData", gcu.getTripRoutesMap(routes));
        model.addAttribute("trips", trips);

        return "trips/trip";
    }
    @RequestMapping("/trip/create")
    public String getCreateMovieShow(Model model){
        Iterable<TrainEntity> trains = tripService.getTrainRepo().findAll();
        Iterable<RouteEntity> routes = tripService.getRouteRepo().findAll();
        model.addAttribute("trains", trains);
        model.addAttribute("routes", routes);
        System.out.println(1);
        return "/trips/create-trip";
    }
    @PostMapping("/trip/create")
    public String createTrain(@Valid Trip trip, HttpServletRequest request, RedirectAttributes redirectAttributes){
        System.out.println(2);
        String referer = request.getHeader("Referer");
        String createTripCheck = tripService.createTripCheck(trip);

        if (createTripCheck != null){
            redirectAttributes.addFlashAttribute("wrongData", createTripCheck);
            return "redirect:"+ referer;
        }

        TripEntity tripEntity = new TripEntity(trip.getTrainEntity(), trip.getRouteEntity(), trip.getDepartureTime());
        tripService.getTripRepo().save(tripEntity);

        return "redirect:/trip";
    }

    @RequestMapping("/trip/edit")
    public String getEditTrip(@Valid Trip trip, Model model) {
        Iterable<TrainEntity> trains = tripService.getTrainRepo().findAll();
        Iterable<RouteEntity> routes = tripService.getRouteRepo().findAll();

        model.addAttribute("id", trip.getId());
        model.addAttribute("routes", routes);
        model.addAttribute("trains", trains);
        model.addAttribute("departureTime", trip.getDepartureTime());

        return "/trips/edit-trip";
    }

    @PostMapping("/trip/edit")
    public String editTrip(@Valid Trip trip,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");
        String existenceTripCheck = tripService.existenceTripCheck(trip);
        if (existenceTripCheck != null){
            redirectAttributes.addFlashAttribute("wrongData",existenceTripCheck);
            return "redirect:"+ referer;
        }

        TripEntity tripEntity = tripService.getTripRepo().findById(trip.getId()).get();
        TripEntity returnEntity = new TripEntity(trip.getTrainEntity(), trip.getRouteEntity(), trip.getDepartureTime());
        BeanUtils.copyProperties(returnEntity, tripEntity, "id");
        tripService.getTripRepo().save(tripEntity);

        return "redirect:/trip";

    }


}
