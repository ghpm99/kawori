/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.web.controller;

import java.time.Duration;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bot.KaworiSpring.service.StatusService;

/**
 *
 * @author ghpm9
 */
@Controller
public class InicioController {

    @Autowired
    private StatusService statusService;

    
    public String status(Model model) {
        model.addAttribute("status", statusService.getStatusBot());
        model.addAttribute("cmdReceived", statusService.getCmdReceived());

        Duration duration = Duration.between(statusService.getTimeOn(),ZonedDateTime.now());
        model.addAttribute("time", "Horas:" + duration.toHours() + " Minutos:" + duration.toMinutes() + " Segundos:");
        return "inicio-status";
    }

}
