package com.bot.KaworiSpring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.LogService;

@Controller
public class LogController {

	@Autowired
	private LogService logService;

	public List<Log> log() {
		return logService.getEvents();
	}

	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

	public String page(Model model) {
		return "events-log";
	}

}
