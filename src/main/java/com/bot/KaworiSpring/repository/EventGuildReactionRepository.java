/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.repository;

import com.bot.KaworiSpring.model.EventGuildReaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ghpm9
 */
public interface EventGuildReactionRepository extends JpaRepository<EventGuildReaction, Long> {
    
}