/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ghpm9
 */
@Service
public class StatusService {
	
	@Autowired
	private GuildaService guildaService;
	
	@Autowired
	private OperatorService operatorService;

    private String statusBot = "-";

    private final ZonedDateTime timeOn = ZonedDateTime.now();

    private long cmdReceived = 0;
    

    public String getStatusBot() {
        return statusBot;
    }

    public void setStatusBot(String statusBot) {
        this.statusBot = statusBot;
    }

    public ZonedDateTime getTimeOn() {
        return timeOn;
    }

    public long getCmdReceived() {
        return cmdReceived;
    }

    public void increaseCmdReceived() {
        this.cmdReceived++;
    }    
    
    public long getGuildCount() {
    	return guildaService.count();
    }
    
    public long getUserCount() {
    	return operatorService.count();
    }
    
}
