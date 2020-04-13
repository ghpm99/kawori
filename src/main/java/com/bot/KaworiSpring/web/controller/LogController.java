package com.bot.KaworiSpring.web.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.LogService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogController {
    
    @Autowired
    private SimpMessageSendingOperations template;
    @Autowired
    private LogService logService;
    
    @MessageMapping("/input")
    @SendTo("/bot/all")
    public ArrayList<Log> log() {               
        return logService.getEvents();
    }

    @MessageExceptionHandler
    @SendToUser("/topic/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
    
    
    public void sendLog(Log log){
        System.out.println(log.getEvent());
        this.template.convertAndSend("/bot/log",log);
    }
    
    @GetMapping("/events/log")
    public String page(Model model){       
        return "events-log";
    }

}
