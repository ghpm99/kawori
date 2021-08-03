package com.bot.KaworiSpring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Log;

/**
 * The Interface LogRepository.
 */
public interface LogRepository extends MongoRepository<Log, String>{

}
