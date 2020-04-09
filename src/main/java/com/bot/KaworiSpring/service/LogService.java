package com.bot.KaworiSpring.service;

import java.util.ArrayList;

import com.bot.KaworiSpring.model.Log;

public class LogService {

    private static LogService instance;    
    
    private ArrayList<Log> events = new ArrayList<>();

    private boolean online;

    public static LogService getInstance(){
        if(instance == null){
            instance = new LogService();
        }
        
        return instance;
    }
    
    public void addEvent(Log log) {
        events.add(log);
    }

    public ArrayList<Log> getEvents() {
        return events;
    }

}
