package com.example.demo.controller;


import com.example.demo.service.ThreadSimulationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadController {
    private final ThreadSimulationService simulationService;

    public ThreadController(ThreadSimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/start-threads")
    public void startThreads(@RequestParam(defaultValue = "5") int numberOfThreads) {
        simulationService.startThreadSimulation(numberOfThreads);
    }
}