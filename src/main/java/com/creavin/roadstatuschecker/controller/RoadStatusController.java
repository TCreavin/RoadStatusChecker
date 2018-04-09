package com.creavin.roadstatuschecker.controller;

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
    public StatusService statusService;

    /**
     * Display the status checker form.
     *
     * @param model the model attached to the view.
     * @return the view.
     */
    @GetMapping("/statusChecker")
    public String showStatusChecker() {
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
    public String checkRoadStatus(@RequestParam(required = false) final String road, final Model model) {
        //call status service
        if (road != null) {
            model.addAttribute("roadStatus", statusService.roadStatus(road));
        } else {
            model.addAttribute("error", "Please provide a road to check the status of.");
        }
        return "status";
    }
}
