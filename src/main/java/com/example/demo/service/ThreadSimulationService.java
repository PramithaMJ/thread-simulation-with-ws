package com.example.demo.service;

import com.example.demo.model.ThreadStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ThreadSimulationService {
    private final SimpMessagingTemplate messagingTemplate;
    private final ExecutorService executorService;
    private final AtomicInteger threadCounter;

    public ThreadSimulationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.executorService = Executors.newFixedThreadPool(10);
        this.threadCounter = new AtomicInteger(0);
    }

    public void startThreadSimulation(int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(createThread());
        }
    }

    private Runnable createThread() {
        return () -> {
            String threadId = "Thread-" + threadCounter.incrementAndGet();
            ThreadStatus status = new ThreadStatus();
            status.setThreadId(threadId);
            status.setStartTime(LocalDateTime.now());
            status.setStatus("STARTED");

            try {
                for (int progress = 0; progress <= 100; progress += 10) {
                    status.setProgress(progress);
                    status.setStatus(progress == 100 ? "COMPLETED" : "RUNNING");

                    // Broadcast thread status
                    messagingTemplate.convertAndSend("/topic/thread-status", status);

                    Thread.sleep(1000); // Simulating work
                }
                status.setEndTime(LocalDateTime.now());
            } catch (InterruptedException e) {
                status.setStatus("INTERRUPTED");
                Thread.currentThread().interrupt();
            } finally {
                messagingTemplate.convertAndSend("/topic/thread-status", status);
            }
        };
    }

    public void shutdownThreads() {
        executorService.shutdownNow();
    }
}
