package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.repository.LogRepository;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	public void addEvent(Log log) {
		Log logSaved = logRepository.save(log);
		System.out.println(logSaved.toString());
	}

	public List<Log> getEvents() {
		return logRepository.findAll();
	}

}
