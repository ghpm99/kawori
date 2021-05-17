package com.bot.KaworiSpring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bot.KaworiSpring.service.StatusService;

@RestController
public class StatusController {

	@Autowired
	private StatusService statusService;
		
	@GetMapping("/status")
	public DataResponse status() {
		System.out.println("Chamou");
		DataResponse data = new DataResponse();
		data.setStatus(statusService.getStatusBot());
		data.setCmdReceived(statusService.getCmdReceived());
		data.setGuildCount(statusService.getGuildCount());
		data.setUserCount(statusService.getUserCount());
		return data;
	}
	
	class DataResponse{
		private String status;		
		private long cmdReceived;
		private long guildCount;
		private long userCount;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public long getCmdReceived() {
			return cmdReceived;
		}
		public void setCmdReceived(long cmdReceived) {
			this.cmdReceived = cmdReceived;
		}
		public long getGuildCount() {
			return guildCount;
		}
		public void setGuildCount(long guildCount) {
			this.guildCount = guildCount;
		}
		public long getUserCount() {
			return userCount;
		}
		public void setUserCount(long userCount) {
			this.userCount = userCount;
		}
		
		
		
	}
}
