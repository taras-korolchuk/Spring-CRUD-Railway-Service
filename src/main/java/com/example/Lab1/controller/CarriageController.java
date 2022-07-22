package com.example.Lab1.controller;

import com.example.Lab1.entity.CarriageEntity;
import com.example.Lab1.entity.TrainEntity;
import com.example.Lab1.model.Carriage;
import com.example.Lab1.service.CarriageService;

import com.example.Lab1.service.TrainService;
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
public class CarriageController {
//    @Autowired
//    private GoogleChartsUtils gcu;

    @Autowired
    CarriageService carriageService;
    @Autowired
    TrainService trainService;

    @RequestMapping("/carriage")
    public String getCarriage(Model model) {

        Iterable<CarriageEntity> carriages = carriageService.getCarriageRepo().findAll();

        model.addAttribute("carriages", carriages);

        return "carriages/carriage";
    }
    @RequestMapping("/carriage1")
    public String getCarriage1(Model model) {
        var train = trainService.getTrainRepo().findTrainEntityByTrainNumber((long)710);
        Iterable<CarriageEntity> carriages = carriageService.getCarriageRepo().findByTrainEntity(train);

        model.addAttribute("carriages", carriages);

        return "carriages/carriage";
    }

    @RequestMapping("/carriage/create")
    public String getCreateCarriage(Model model) {
        Iterable<TrainEntity> trains = carriageService.getTrainRepo().findAll();
        model.addAttribute("trains", trains);
        return "/carriages/create-carriage";
    }

    @PostMapping("/carriage/create")
    public String createCarriage(@Valid Carriage carriage, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        String referer = request.getHeader("Referer");
        String createCarriageCheck = carriageService.createCarriageCheck(carriage);
        if (createCarriageCheck != null){
            redirectAttributes.addFlashAttribute("wrongData", createCarriageCheck);
            return "redirect:" + referer;
        }

        TrainEntity trainEntity = carriageService.getTrainRepo().findById(carriage.getTrainEntity().getTrainId()).get();
        CarriageEntity carriageEntity = new CarriageEntity(carriage.getNumber(),carriage.getSeatNumber(),trainEntity);
        carriageService.getCarriageRepo().save(carriageEntity);

        return "redirect:/carriage";
    }

    @RequestMapping("/carriage/edit")
    public String getEditCarriage(@Valid Carriage carriage, Model model) {
        Iterable<TrainEntity> trains = carriageService.getTrainRepo().findAll();

        model.addAttribute("id", carriage.getId());
        model.addAttribute("number", carriage.getNumber());
        model.addAttribute("seatNumber", carriage.getSeatNumber());
        model.addAttribute("trainEntity", carriage.getTrainEntity());
        model.addAttribute("trains", trains);

        return "/carriages/edit-carriage";
    }

    @PostMapping("/carriage/edit")
    public String editCarriage(@Valid Carriage carriage,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");
        String existenceCarriageCheck = carriageService.existenceCarriageCheck(carriage);
        if (existenceCarriageCheck != null){
            redirectAttributes.addFlashAttribute("wrongData",existenceCarriageCheck);
            return "redirect:"+ referer;
        }

        CarriageEntity carriageEntity = carriageService.getCarriageRepo().findById(carriage.getId()).get();
        CarriageEntity returnEntity = new CarriageEntity( carriage.getNumber(), carriage.getSeatNumber(), carriage.getTrainEntity());

        BeanUtils.copyProperties(returnEntity, carriageEntity, "id");
        carriageService.getCarriageRepo().save(carriageEntity);

        return "redirect:/carriage";
    }
}