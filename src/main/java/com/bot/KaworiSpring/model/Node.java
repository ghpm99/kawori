package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Node extends Model{
	
	private String tier;

	private String name;

	private String channel;

	private String server;

	private int limitPlayer;

	private int limitHeroi;

	private int dayOfWeek;

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getLimitPlayer() {
		return limitPlayer;
	}

	public void setLimitPlayer(int limitPlayer) {
		this.limitPlayer = limitPlayer;
	}

	public int getLimitHeroi() {
		return limitHeroi;
	}

	public void setLimitHeroi(int limitHeroi) {
		this.limitHeroi = limitHeroi;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}


}
