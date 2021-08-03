package com.bot.KaworiSpring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.LogService;

// TODO: Auto-generated Javadoc
/**
 * The Class LogController.
 */
@Controller
public class LogController {

	/** The log service. */
	@Autowired
	private LogService logService;

	/**
	 * Log.
	 *
	 * @return the list
	 */
	public List<Log> log() {
		return logService.getEvents();
	}

	/**
	 * Handle exception.
	 *
	 * @param exception the exception
	 * @return the string
	 */
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

	/**
	 * Page.
	 *
	 * @param model the model
	 * @return the string
	 */
	public String page(Model model) {
		return "events-log";
	}

}
