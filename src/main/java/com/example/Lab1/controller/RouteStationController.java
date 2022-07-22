package com.example.Lab1.controller;

import com.example.Lab1.entity.*;
import com.example.Lab1.model.Carriage;
import com.example.Lab1.model.RouteStation;
import com.example.Lab1.repository.RouteStationRepo;
import com.example.Lab1.service.CarriageService;
import com.example.Lab1.service.GoogleChartsUtils;
import com.example.Lab1.service.RouteStationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RouteStationController {
    @Autowired
    CarriageService carriageService;

    @Autowired
    RouteStationService routeStationService;

    @Autowired
    GoogleChartsUtils gcu;

    @RequestMapping("/station")
    public String getCarriage(Model model) {
        Iterable<RouteEntity> routes = routeStationService.getRouteRepo().findAll();
        Iterable<RouteStationEntity> routeStations = routeStationService.getRouteStationRepo().findAll();
        model.addAttribute("routesData", gcu.getRouteStationByRouteMap(routes));
        model.addAttribute("stations", routeStations);
        return "stations/station";


    }
    @RequestMapping("/station1")
    public String getCarriage1(Model model) {
        Iterable<RouteEntity> routes = routeStationService.getRouteRepo().findAll();
        var route = routeStationService.getRouteRepo().findByName("Chernihiv-Odesa");
        Iterable<RouteStationEntity> routeStations = routeStationService.getRouteStationRepo().findByRouteEntity(route);
        model.addAttribute("routesData", gcu.getRouteStationByRouteMap(routes));
        model.addAttribute("stations", routeStations);
        return "stations/station";


    }

    @RequestMapping("/station/create")
    public String getCreateCarriage(Model model) {
        Iterable<StationEntity> stations = routeStationService.getStationRepo().findAll();
        Iterable<RouteEntity> routes = routeStationService.getRouteRepo().findAll();
        model.addAttribute("stations", stations);
        model.addAttribute("routes", routes);
        return "/stations/create-station";
    }

    @PostMapping("/station/create")
    public String createCarriage(@Valid RouteStation routeStation, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        String referer = request.getHeader("Referer");
        String createRouteStationCheck = routeStationService.createRouteStationCheck(routeStation);
        if (createRouteStationCheck != null){
            redirectAttributes.addFlashAttribute("wrongData", createRouteStationCheck);
            return "redirect:" + referer;
        }

        StationEntity stationEntity = routeStationService.getStationRepo().findById(routeStation.getStationEntity().getId()).get();
        RouteEntity routeEntity = routeStationService.getRouteRepo().findById(routeStation.getRouteEntity().getId()).get();
        RouteStationEntity routeStationEntity = new RouteStationEntity(stationEntity, routeEntity);
        routeStationService.getRouteStationRepo().save(routeStationEntity);

        return "redirect:/station";
    }

    @RequestMapping("/station/edit")
    public String getEditStation(@Valid RouteStation routeStation, Model model) {
        Iterable<StationEntity> stations = routeStationService.getStationRepo().findAll();
        Iterable<RouteEntity> routes = routeStationService.getRouteRepo().findAll();

        model.addAttribute("id", routeStation.getId());
        model.addAttribute("routes", routes);
        model.addAttribute("stations", stations);

        return "/stations/edit-station";
    }

    @PostMapping("/station/edit")
    public String editCarriage(@Valid RouteStation routeStation,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");
        String existenceRouteStationCheck = routeStationService.existenceRouteStationCheck(routeStation);
        if (existenceRouteStationCheck != null){
            redirectAttributes.addFlashAttribute("wrongData",existenceRouteStationCheck);
            return "redirect:"+ referer;
        }

        RouteStationEntity routeStationEntity = routeStationService.getRouteStationRepo().findById(routeStation.getId()).get();
        RouteStationEntity returnEntity = new RouteStationEntity(routeStation.getStationEntity(), routeStation.getRouteEntity());
        BeanUtils.copyProperties(returnEntity, routeStationEntity, "id");
        routeStationService.getRouteStationRepo().save(routeStationEntity);

        return "redirect:/station";

    }
}
