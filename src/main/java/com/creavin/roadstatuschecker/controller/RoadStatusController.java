package com.creavin.roadstatuschecker.controller;

import com.creavin.roadstatuschecker.model.RoadStatus;
import com.creavin.roadstatuschecker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller dealing with road status requests.
 */
@Controller
public class RoadStatusController {
    /**
     * The status service.
     */
    @Autowired
    private StatusService statusService;

    /**
     * Display the status checker form.
     *
     * @return the view.
     */
    @GetMapping("/statusChecker")
    public final String showStatusChecker() {
        return "status";
    }

    /**
     * Check the status of a given road.
     *
     * @param road  the road to check.
     * @param model the  model.
     * @return the view.
     */
    @GetMapping("/checkStatus")
    public final String checkRoadStatus(@RequestParam(required = false) final String road, final Model model) {
        //call status service
        RoadStatus rs = statusService.roadStatus(road);
        if (rs != null) {
            model.addAttribute("roadStatus", rs);
        } else {
            model.addAttribute("error", "Sorry we cannot currently check the status of your road");
            System.out.println("Please check your endpoints and your api credentials.");
        }
        return "status";
    }
}
