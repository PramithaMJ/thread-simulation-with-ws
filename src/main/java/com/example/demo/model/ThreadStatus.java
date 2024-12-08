package com.example.demo.model;

import lombok.Data;
import java.time.LocalDateTime;

//@Data
public class ThreadStatus {
    private String threadId;
    private String status;
    private double progress;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ThreadStatus() {
    }

    public ThreadStatus(String threadId, String status, double progress, LocalDateTime startTime, LocalDateTime endTime) {
        this.threadId = threadId;
        this.status = status;
        this.progress = progress;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}