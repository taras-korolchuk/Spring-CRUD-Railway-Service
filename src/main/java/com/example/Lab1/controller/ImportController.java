package com.example.Lab1.controller;

import com.example.Lab1.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class ImportController {
    @Autowired
    private ImportService importService;

    @PostMapping("/train")
    public String uploadTrain(@RequestParam("file") MultipartFile reapExcelDataFile,
                              HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {

        String referer = request.getHeader("Referer");
        String check = importService.importTrain(reapExcelDataFile);
        if (check != null){
            redirectAttributes.addFlashAttribute("wrongData", check);
            return "redirect:" + referer;
        }
        return "redirect:" + referer;
    }
    @PostMapping("/route")
    public String uploadRoute(@RequestParam("file") MultipartFile reapExcelDataFile,
                               HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {

        String referer = request.getHeader("Referer");
        String check = importService.importRoute(reapExcelDataFile);
        if (check != null){
            redirectAttributes.addFlashAttribute("wrongData", check);
            return "redirect:" + referer;
        }
        return "redirect:" + referer;
    }
    @PostMapping("/station")
    public String uploadStation(@RequestParam("file") MultipartFile reapExcelDataFile,
                              HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {

        String referer = request.getHeader("Referer");
        String check = importService.importRouteStation(reapExcelDataFile);
        if (check != null){
            redirectAttributes.addFlashAttribute("wrongData", check);
            return "redirect:" + referer;
        }
        return "redirect:" + referer;
    }

    @PostMapping("/carriage")
    public String uploadCarriage(@RequestParam("file") MultipartFile reapExcelDataFile,
                                   HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {

        String referer = request.getHeader("Referer");
        String check = importService.importCarriage(reapExcelDataFile);
        if (check != null){
            redirectAttributes.addFlashAttribute("wrongData", check);
            return "redirect:" + referer;
        }
        return "redirect:" + referer;
    }


    @PostMapping("/trip")
    public String uploadTrip(@RequestParam("file") MultipartFile reapExcelDataFile,
                                  HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {

        String referer = request.getHeader("Referer");
        String check = importService.importTrip(reapExcelDataFile);
        if (check != null){
            redirectAttributes.addFlashAttribute("wrongData", check);
            return "redirect:" + referer;
        }

        return "redirect:" + referer;
    }
}
