package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.repository.LogRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class LogService.
 */
@Service
public class LogService {

	/** The log repository. */
	@Autowired
	private LogRepository logRepository;

	/**
	 * Adds the event.
	 *
	 * @param log the log
	 */
	public void addEvent(Log log) {
		Log logSaved = logRepository.save(log);
		System.out.println(logSaved.toString());
	}

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<Log> getEvents() {
		return logRepository.findAll();
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Log> findAll(Pageable pageable){
		return logRepository.findAll(pageable);
	}
	
}
