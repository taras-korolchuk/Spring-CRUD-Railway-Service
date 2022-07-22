package com.example.Lab1.controller;

import com.example.Lab1.entity.TripEntity;
import com.example.Lab1.service.GoogleChartsUtils;
import com.example.Lab1.service.TrainService;
//import com.google.common.collect.Lists;
//import com.example.Lab1.entity.CinemaEntity;
//import com.example.Lab1.entity.MovieEntity;
//import com.example.Lab1.entity.MovieShowEntity;
//import com.example.Lab1.model.Cinema;
//import com.example.Lab1.service.GoogleChartsUtils;
import com.example.Lab1.entity.TrainEntity;
import com.example.Lab1.model.Train;
import org.apache.commons.compress.utils.Lists;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainController {
    @Autowired
    private GoogleChartsUtils gcu;

    @Autowired
    TrainService trainService;

    @RequestMapping("/train")
    public String getTrain(Model model){

        Iterable<TrainEntity> trains = trainService.getTrainRepo().findAll();

        model.addAttribute("trains", trains);

        return "/trains/train";
    }

    @RequestMapping("/train/create")
    public String getCreateTrain(){
        return "/trains/create-train";
    }

    @PostMapping("/train/create")
    public String createTrain(@Valid Train train, HttpServletRequest request, RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");
        String createTrainCheck = trainService.createTrainCheck(train);

        if (createTrainCheck != null){
            redirectAttributes.addFlashAttribute("wrongData", createTrainCheck);
            return "redirect:"+ referer;
        }

        TrainEntity trainEntity = new TrainEntity(train.getTrainNumber());
        System.out.println(trainEntity.getTrainNumber());
        trainService.getTrainRepo().save(trainEntity);

        return "redirect:/train";
    }

    @RequestMapping("/train/edit")
    public String getEditTrain(@Valid Train train, Model model) {

        model.addAttribute("trainId", train.getTrainId());
        model.addAttribute("trainNumber", train.getTrainNumber());

        return "/trains/edit-train";
    }

    @PostMapping("/train/edit")
    public String editTrain(@Valid Train train,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes){

        String referer = request.getHeader("Referer");
        String existTrainCheck = trainService.existenceTrainCheck(train);
        if (existTrainCheck != null){
            redirectAttributes.addFlashAttribute("wrongData",existTrainCheck);
            return "redirect:"+ referer;
        }

        TrainEntity trainEntity = trainService.getTrainRepo().findById(train.getTrainId()).get();
        TrainEntity returnEntity = new TrainEntity(train.getTrainNumber());

        BeanUtils.copyProperties(returnEntity, trainEntity, "trainId");
        trainService.getTrainRepo().save(trainEntity);

        return "redirect:/train";
    }

    @RequestMapping("/train/delete")
    public String getDeleteTrain(@Valid Train train, Model model){


        return "/trains/delete-train";
    }

    @Transactional
    @PostMapping("/train/delete")
    public String deleteTrain(@Valid Train train, RedirectAttributes redirectAttributes){


        return "redirect:/train";
    }

    @RequestMapping("/train/details")
        public String showDetails(){
        return "/trains/train-details";
    }
}