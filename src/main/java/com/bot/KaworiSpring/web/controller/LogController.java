package com.bot.KaworiSpring.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.LogService;

@Controller
public class LogController {
    
    
    
    @Autowired
    private LogService logService;
    
    
    public ArrayList<Log> log() {               
        return logService.getEvents();
    }

    
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
    
    
    public void sendLog(Log log){
        System.out.println(log.getEvent());
       
    }
    
    
    public String page(Model model){       
        return "events-log";
    }

}
