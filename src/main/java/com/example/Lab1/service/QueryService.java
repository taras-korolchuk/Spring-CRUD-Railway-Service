package com.example.Lab1.service;



import com.example.Lab1.entity.CarriageEntity;
import com.example.Lab1.repository.CarriageRepo;
import com.example.Lab1.repository.TrainRepo;
import com.example.Lab1.repository.queries.CarriageQueryRepo;
import com.example.Lab1.repository.queries.RouteQueryRepo;
import com.example.Lab1.repository.queries.TrainQueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QueryService {
    @Autowired
    private CarriageQueryRepo carriageQueryRepo;
    @Autowired
    private TrainQueryRepo trainQueryRepo;
    @Autowired
    private RouteQueryRepo routeQueryRepo;



    public String solveQuery1(Long trainNumber){
        String result= "";
        List<String> queryList =  carriageQueryRepo.getQuery1(trainNumber);
        for (String number: queryList) {
            result += number + "; ";
        }
        return result;
    }
    public String solverQuery2(Long trainId){
        String result="";
        List<String> queryList =  carriageQueryRepo.getQuery2(trainId);
        for (String el: queryList) {
            result += el + "; ";
        }
            return result;
    }
    public String solveQuery3(Long seatNumber){
        String result="";
        List<String> queryList =  trainQueryRepo.getQuery3(seatNumber);
        for (String el: queryList) {
            result += el + "; ";
        }
        return result;
    }
    public String solveQuery4(Long count){
        String result="";
        List<String> queryList =  routeQueryRepo.getQuery4(count);
        for (String el: queryList) {
            result += el + "; ";
        }
        return result;
    }

    public String solveQuery5(String station){
        String result="";
        List<String> queryList =  routeQueryRepo.getQuery5(station);
        for (String el: queryList) {
            result += el + "; ";
        }
        return result;
    }
    public String solveQuery6(Long route){
        String result="";
        List<String> queryList =  routeQueryRepo.getQuery6(route);
        for (String el: queryList) {
            result += el + "; ";
        }
        return result;
    }
    public String solveQuery7(Long route){
        String result="";
        List<String> queryList =  routeQueryRepo.getQuery7(route);
        for (String el: queryList) {
            result += el + "; ";
        }
        return result;
    }
    public String solveQuery8(Long count){
        String result="";
        List<String> queryList =  trainQueryRepo.getQuery8(count);
        for (String el: queryList) {
            result += el + "; ";
        }
        return result;
    }



}
