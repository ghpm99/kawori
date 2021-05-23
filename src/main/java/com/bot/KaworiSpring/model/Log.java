package com.bot.KaworiSpring.model;

import java.util.Date;

public class Log extends Model {

	private Date hour;
	private String event;
	private String guild;
	private String user;
	private String status;

	public Log(Date hour, String event, String guild, String user, String status) {
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

	public String getGuild() {
		return guild;
	}

	public void setGuild(String guild) {
		this.guild = guild;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID:" + getId() + " : " + getHour().toString() + " : " + getEvent() + " Guild:" + getGuild() + " User:"
				+ getUser() + " Status:" + getStatus();
	}

}
