package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/start-threads")
    public void startThreads(int numberOfThreads) {
        // This method will be triggered from the client
        // Actual thread management will be in the service
    }
}