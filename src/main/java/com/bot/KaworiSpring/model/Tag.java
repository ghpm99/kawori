package com.bot.KaworiSpring.model;

import java.awt.Color;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection =  "role")
public class Tag extends Model{

	private long idGuild;
	
	private long idRole;
	
	private String name;
	
	private int red;
	
	private int green;
	
	private int blue;
	
	private int position;
	
	private boolean administrator;
	
	private boolean manageChannels;
	
	private boolean managePermissions;
	
	private boolean manageRoles;
	
	private boolean manageServer;
	
	private boolean messageManage;
	
	private boolean messageMentionEveryone;
	
	private boolean messageRead;
	
	private boolean messageWrite;
	
	private boolean nicknameChange;
	
	private boolean nicknameManage;
	
	private boolean botRole;
	
	private boolean active;
	
	private boolean cmdFun;
	
	private boolean cmdUtil;
	
	private boolean cmdBuild;
	
	private boolean cmdNodeWar;
	
	private boolean cmdAdm;
	
	private boolean cmdRank;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public long getIdGuild() {
		return idGuild;
	}

	public void setIdGuild(long idGuild) {
		this.idGuild = idGuild;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public boolean isManageChannels() {
		return manageChannels;
	}

	public void setManageChannels(boolean manageChannels) {
		this.manageChannels = manageChannels;
	}

	public boolean isManagePermissions() {
		return managePermissions;
	}

	public void setManagePermissions(boolean managePermissions) {
		this.managePermissions = managePermissions;
	}

	public boolean isManageRoles() {
		return manageRoles;
	}

	public void setManageRoles(boolean manageRoles) {
		this.manageRoles = manageRoles;
	}

	public boolean isManageServer() {
		return manageServer;
	}

	public void setManageServer(boolean manageServer) {
		this.manageServer = manageServer;
	}

	public boolean isMessageManage() {
		return messageManage;
	}

	public void setMessageManage(boolean messageManage) {
		this.messageManage = messageManage;
	}

	public boolean isMessageMentionEveryone() {
		return messageMentionEveryone;
	}

	public void setMessageMentionEveryone(boolean messageMentionEveryone) {
		this.messageMentionEveryone = messageMentionEveryone;
	}

	public boolean isMessageRead() {
		return messageRead;
	}

	public void setMessageRead(boolean messageRead) {
		this.messageRead = messageRead;
	}

	public boolean isMessageWrite() {
		return messageWrite;
	}

	public void setMessageWrite(boolean messageWrite) {
		this.messageWrite = messageWrite;
	}

	public boolean isNicknameChange() {
		return nicknameChange;
	}

	public void setNicknameChange(boolean nicknameChange) {
		this.nicknameChange = nicknameChange;
	}

	public boolean isNicknameManage() {
		return nicknameManage;
	}

	public void setNicknameManage(boolean nicknameManage) {
		this.nicknameManage = nicknameManage;
	}

	public boolean isBotRole() {
		return botRole;
	}

	public void setBotRole(boolean botRole) {
		this.botRole = botRole;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setColor(Color color) {
		if(color == null) color = new Color(0,0,0);
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
	}

	public boolean isCmdFun() {
		return cmdFun;
	}

	public void setCmdFun(boolean cmdFun) {
		this.cmdFun = cmdFun;
	}

	public boolean isCmdUtil() {
		return cmdUtil;
	}

	public void setCmdUtil(boolean cmdUtil) {
		this.cmdUtil = cmdUtil;
	}

	public boolean isCmdBuild() {
		return cmdBuild;
	}

	public void setCmdBuild(boolean cmdBuild) {
		this.cmdBuild = cmdBuild;
	}

	public boolean isCmdNodeWar() {
		return cmdNodeWar;
	}

	public void setCmdNodeWar(boolean cmdNodeWar) {
		this.cmdNodeWar = cmdNodeWar;
	}

	public boolean isCmdAdm() {
		return cmdAdm;
	}

	public void setCmdAdm(boolean cmdAdm) {
		this.cmdAdm = cmdAdm;
	}

	public boolean isCmdRank() {
		return cmdRank;
	}

	public void setCmdRank(boolean cmdRank) {
		this.cmdRank = cmdRank;
	}

	
	
}
