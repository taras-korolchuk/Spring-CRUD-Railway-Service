package com.example.Lab1.controller;

import com.example.Lab1.service.RouteService;
//import com.google.common.collect.Lists;
//import com.example.Lab1.entity.CinemaEntity;
//import com.example.Lab1.entity.MovieEntity;
//import com.example.Lab1.entity.MovieShowEntity;
//import com.example.Lab1.model.Cinema;
//import com.example.Lab1.service.GoogleChartsUtils;
import com.example.Lab1.entity.RouteEntity;
import com.example.Lab1.model.Route;
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
public class RouteController {
//    @Autowired
//    private GoogleChartsUtils gcu;

    @Autowired
    RouteService routeService;

    @RequestMapping("/route")
    public String getRoute(Model model){

        Iterable<RouteEntity> routes = routeService.getRouteRepo().findAll();

        model.addAttribute("routes", routes);

        return "/routes/route";
    }

    @RequestMapping("/route/create")
    public String getCreateRoute(){
        return "/routes/create-route";
    }

    @PostMapping("/route/create")
    public String createRoute(@Valid Route route, HttpServletRequest request, RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");
        String createRouteCheck = routeService.createRouteCheck(route);
        if (createRouteCheck != null){
            redirectAttributes.addFlashAttribute("wrongData", createRouteCheck);
            return "redirect:"+ referer;
        }

        RouteEntity routeEntity = new RouteEntity(route.getName());
        routeService.getRouteRepo().save(routeEntity);

        return "redirect:/route";
    }

    @RequestMapping("/route/edit")
    public String getEditRoute(@Valid Route route, Model model) {

        model.addAttribute("id", route.getId());
        model.addAttribute("name", route.getName());

        return "/routes/edit-route";
    }

    @PostMapping("/route/edit")
    public String editRoute(@Valid Route route,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");
        String existRouteCheck = routeService.existenceRouteCheck(route);
        if (existRouteCheck != null){
            redirectAttributes.addFlashAttribute("wrongData",existRouteCheck);
            return "redirect:"+ referer;
        }

        RouteEntity routeEntity = routeService.getRouteRepo().findById(route.getId()).get();
        RouteEntity returnEntity = new RouteEntity(route.getName());

        BeanUtils.copyProperties(returnEntity, routeEntity, "id");
        routeService.getRouteRepo().save(routeEntity);

        return "redirect:/route";
    }

    @RequestMapping("/route/delete")
    public String getDeleteRoute(@Valid Route route, Model model){

        model.addAttribute("id", route.getId());
        model.addAttribute("name", route.getName());

        return "/routes/delete-route";
    }

    @Transactional
    @PostMapping("/route/delete")
    public String deleteRoute(@Valid Route route, RedirectAttributes redirectAttributes){



        return "redirect:/route";
    }

    @RequestMapping("/route/details")
    public String getDetailsRoute(@Valid Route route, Model model) {


        return "/routes/details-route";
    }
}