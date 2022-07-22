package com.example.Lab1.controller;

import com.example.Lab1.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class QueryController {
    @Autowired
    QueryService queryService;



    @RequestMapping("/query")
    String getQueryView() {
        return "/queries/query";
    }

    @PostMapping("/query1")
    String query1(@Valid Long trainNumber, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){
        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("query1", queryService.solveQuery1(trainNumber));
        System.out.println(trainNumber);
        return "redirect:" + referer;
    }

    @PostMapping("/query2")
    String query2(@Valid Long trainId, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){
        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("query2", queryService.solverQuery2(trainId));
        return "redirect:" + referer;
    }

    @PostMapping("/query3")
    String query3(@Valid Long seatNumber, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("query3", queryService.solveQuery3(seatNumber));
        return "redirect:" + referer;
    }

    @PostMapping("/query4")
    String query4(@Valid Long count, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("query4", queryService.solveQuery4(count));
        return "redirect:" + referer;
    }

    @PostMapping("/query5")
    String query5(@Valid String station, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("query5", queryService.solveQuery5(station));
        return "redirect:" + referer;
    }

    @PostMapping("/query6")
    String query6(@Valid Long route, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("query6", queryService.solveQuery6(route));
        return "redirect:" + referer;
    }

    @PostMapping("/query7")
    String query7(@Valid Long route, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("query7", queryService.solveQuery7(route));
        return "redirect:" + referer;
    }

    @PostMapping("/query8")
    String query8(@Valid Long count, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        String referer = request.getHeader("Referer");
        redirectAttributes.addFlashAttribute("query8", queryService.solveQuery8(count));
        return "redirect:" + referer;
    }
}
