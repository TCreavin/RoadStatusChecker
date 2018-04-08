package com.creavin.roadstatuschecker.controller;

import com.creavin.roadstatuschecker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoadStatus {
    @Autowired
    public StatusService statusService;

    @GetMapping("/statusChecker")
    public String showStatusChecker() {
        return "status";
    }

    @GetMapping("/checkStatus")
    public String checkRoadStatus() {
        //call status service
        System.out.println("yeah check status");
        System.out.println(statusService.roadStatus("test"));
        return "status";
    }
}
