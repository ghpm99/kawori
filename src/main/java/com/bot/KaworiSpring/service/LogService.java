package com.bot.KaworiSpring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.web.controller.LogController;

@Service
public class LogService {

    @Autowired
    private LogController logController;
    
    private ArrayList<Log> events = new ArrayList<>();

    private boolean online;
    
    public void addEvent(Log log) {
        events.add(log);
        logController.sendLog(log);
    }

    public ArrayList<Log> getEvents() {
        return events;
    }

}
