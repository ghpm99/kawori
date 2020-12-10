package com.bot.KaworiSpring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bot.KaworiSpring.discord.command.CommandHandler;

@RestController
public class CommandController {
	
	@GetMapping("/commands")
	public DataResponse command() {
		ArrayList<CommandJSON> commands = new ArrayList<>();
		CommandHandler.commands.forEach((name,command) -> {
			
			CommandJSON commandJson = new CommandJSON();			
			commandJson.setName(name);
			commandJson.setPermission(command.getPermissions().getName());
			commandJson.setHelp(command.help());
			commandJson.setHelpShort(command.helpShort());
			
			commands.add(commandJson);
		});
		
		DataResponse dataResponse = new DataResponse();
		dataResponse.setSuccess(true);
		dataResponse.setCount(commands.size());
		dataResponse.setData(commands);
		
		return dataResponse;
	}

	class DataResponse {
		private Boolean success;
		private Integer count;
		private List<CommandJSON> data;
		
		public Boolean getSuccess() {
			return success;
		}
		public void setSuccess(Boolean success) {
			this.success = success;
		}
		public Integer getCount() {
			return count;
		}
		public void setCount(Integer count) {
			this.count = count;
		}
		public List<CommandJSON> getData() {
			return data;
		}
		public void setData(List<CommandJSON> data) {
			this.data = data;
		}
		
		
		
	}
	
	class CommandJSON{
		private String name;
		private String permission;
		private String help;
		private String helpShort;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPermission() {
			return permission;
		}
		public void setPermission(String permission) {
			this.permission = permission;
		}
		public String getHelp() {
			return help;
		}
		public void setHelp(String help) {
			this.help = help;
		}
		public String getHelpShort() {
			return helpShort;
		}
		public void setHelpShort(String helpShort) {
			this.helpShort = helpShort;
		}
		
		
	}
}
