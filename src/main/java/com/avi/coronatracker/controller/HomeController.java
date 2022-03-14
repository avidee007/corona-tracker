package com.avi.coronatracker.controller;

import com.avi.coronatracker.model.LocationStats;
import com.avi.coronatracker.service.CoronaTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CoronaTrackerService service;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> statsList= service.getStats();
        int total= statsList.stream().mapToInt(LocationStats::getTotalCase).sum();
        model.addAttribute("stats",statsList);
        model.addAttribute("total",total);
        return "home";
    }
}
