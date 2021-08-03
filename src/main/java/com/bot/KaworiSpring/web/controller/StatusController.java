package com.bot.KaworiSpring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bot.KaworiSpring.service.StatusService;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusController.
 */
@RestController
public class StatusController {

	/** The status service. */
	@Autowired
	private StatusService statusService;

	
	/**
	 * Status.
	 *
	 * @return the data response
	 */
	@GetMapping("/status")
	public DataResponse status() {
		DataResponse data = new DataResponse();
		data.setStatus(statusService.getStatusBot());
		data.setCmdReceived(statusService.getCmdReceived());
		data.setGuildCount(statusService.getGuildCount());
		data.setUserCount(statusService.getUserCount());
		return data;
	}

	/**
	 * The Class DataResponse.
	 */
	class DataResponse {
		
		/** The status. */
		private String status;
		
		/** The cmd received. */
		private long cmdReceived;
		
		/** The guild count. */
		private long guildCount;
		
		/** The user count. */
		private long userCount;

		/**
		 * Gets the status.
		 *
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * Sets the status.
		 *
		 * @param status the new status
		 */
		public void setStatus(String status) {
			this.status = status;
		}

		/**
		 * Gets the cmd received.
		 *
		 * @return the cmd received
		 */
		public long getCmdReceived() {
			return cmdReceived;
		}

		/**
		 * Sets the cmd received.
		 *
		 * @param cmdReceived the new cmd received
		 */
		public void setCmdReceived(long cmdReceived) {
			this.cmdReceived = cmdReceived;
		}

		/**
		 * Gets the guild count.
		 *
		 * @return the guild count
		 */
		public long getGuildCount() {
			return guildCount;
		}

		/**
		 * Sets the guild count.
		 *
		 * @param guildCount the new guild count
		 */
		public void setGuildCount(long guildCount) {
			this.guildCount = guildCount;
		}

		/**
		 * Gets the user count.
		 *
		 * @return the user count
		 */
		public long getUserCount() {
			return userCount;
		}

		/**
		 * Sets the user count.
		 *
		 * @param userCount the new user count
		 */
		public void setUserCount(long userCount) {
			this.userCount = userCount;
		}

	}
}
