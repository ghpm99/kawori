/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ghpm9
 */
@Entity
@Table(name = "EventGuildReaction")
public class EventGuildReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private long idUser;
    
    private long idGuild;
    
    @Temporal(TemporalType.DATE)
    private Date time;

    public EventGuildReaction(long idUser, long idGuild, Date time) {
        this.idUser = idUser;
        this.idGuild = idGuild;
        this.time = time;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdGuild() {
        return idGuild;
    }

    public void setIdGuild(long idGuild) {
        this.idGuild = idGuild;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    
}
