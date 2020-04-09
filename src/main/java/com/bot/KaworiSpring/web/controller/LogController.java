package com.bot.KaworiSpring.web.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.LogService;
import java.util.ArrayList;

@Controller
public class LogController {
    

    @MessageMapping("/input")
    @SendTo("/bot/output")
    public ArrayList<Log> log() {
        System.out.println("Log");        
        return LogService.getInstance().getEvents();
    }

    @MessageExceptionHandler
    @SendToUser("/topic/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
