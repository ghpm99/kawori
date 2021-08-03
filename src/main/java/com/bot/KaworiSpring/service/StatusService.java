/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusService.
 *
 * @author ghpm9
 */
@Service
public class StatusService {
	
	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;
	
	/** The operator service. */
	@Autowired
	private OperatorService operatorService;

    /** The status bot. */
    private String statusBot = "-";

    /** The time on. */
    private final ZonedDateTime timeOn = ZonedDateTime.now();

    /** The cmd received. */
    private long cmdReceived = 0;
    

    /**
     * Gets the status bot.
     *
     * @return the status bot
     */
    public String getStatusBot() {
        return statusBot;
    }

    /**
     * Sets the status bot.
     *
     * @param statusBot the new status bot
     */
    public void setStatusBot(String statusBot) {
        this.statusBot = statusBot;
    }

    /**
     * Gets the time on.
     *
     * @return the time on
     */
    public ZonedDateTime getTimeOn() {
        return timeOn;
    }

    /**
     * Gets the cmd received.
     *
     * @return the cmd received
     */
    public long getCmdReceived() {
        return cmdReceived;
    }

    /**
     * Increase cmd received.
     */
    public void increaseCmdReceived() {
        this.cmdReceived++;
    }    
    
    /**
     * Gets the guild count.
     *
     * @return the guild count
     */
    public long getGuildCount() {
    	return guildaService.count();
    }
    
    /**
     * Gets the user count.
     *
     * @return the user count
     */
    public long getUserCount() {
    	return operatorService.count();
    }
    
}
