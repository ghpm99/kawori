package com.bot.KaworiSpring.model;

import java.util.Date;

public class Log {

    private Date hour;
    private String event;
    private long guild;
    private long user;
    private String status;

    public Log(Date hour, String event, long guild, long user, String status) {
        this.hour = hour;
        this.event = event;
        this.guild = guild;
        this.user = user;
        this.status = status;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public long getGuild() {
        return guild;
    }

    public void setGuild(long guild) {
        this.guild = guild;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
