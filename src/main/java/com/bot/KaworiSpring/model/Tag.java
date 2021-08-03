package com.bot.KaworiSpring.model;

import java.awt.Color;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Tag.
 */
@Document(collection = "role")
public class Tag extends Model {

	/** The id guild. */
	private String idGuild;

	/** The id role. */
	private String idRole;

	/** The name. */
	private String name;

	/** The red. */
	private int red;

	/** The green. */
	private int green;

	/** The blue. */
	private int blue;

	/** The position. */
	private int position;

	/** The administrator. */
	private boolean administrator;

	/** The manage channels. */
	private boolean manageChannels;

	/** The manage permissions. */
	private boolean managePermissions;

	/** The manage roles. */
	private boolean manageRoles;

	/** The manage server. */
	private boolean manageServer;

	/** The message manage. */
	private boolean messageManage;

	/** The message mention everyone. */
	private boolean messageMentionEveryone;

	/** The message read. */
	private boolean messageRead;

	/** The message write. */
	private boolean messageWrite;

	/** The nickname change. */
	private boolean nicknameChange;

	/** The nickname manage. */
	private boolean nicknameManage;

	/** The bot role. */
	private boolean botRole;

	/** The active. */
	private boolean active;

	/** The cmd fun. */
	private boolean cmdFun;

	/** The cmd util. */
	private boolean cmdUtil;

	/** The cmd build. */
	private boolean cmdBuild;

	/** The cmd node war. */
	private boolean cmdNodeWar;

	/** The cmd adm. */
	private boolean cmdAdm;

	/** The cmd rank. */
	private boolean cmdRank;

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Gets the id guild.
	 *
	 * @return the id guild
	 */
	public String getIdGuild() {
		return idGuild;
	}

	/**
	 * Sets the id guild.
	 *
	 * @param idGuild the new id guild
	 */
	public void setIdGuild(String idGuild) {
		this.idGuild = idGuild;
	}

	/**
	 * Gets the id role.
	 *
	 * @return the id role
	 */
	public String getIdRole() {
		return idRole;
	}

	/**
	 * Sets the id role.
	 *
	 * @param idRole the new id role
	 */
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the red.
	 *
	 * @return the red
	 */
	public int getRed() {
		return red;
	}

	/**
	 * Sets the red.
	 *
	 * @param red the new red
	 */
	public void setRed(int red) {
		this.red = red;
	}

	/**
	 * Gets the green.
	 *
	 * @return the green
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * Sets the green.
	 *
	 * @param green the new green
	 */
	public void setGreen(int green) {
		this.green = green;
	}

	/**
	 * Gets the blue.
	 *
	 * @return the blue
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * Sets the blue.
	 *
	 * @param blue the new blue
	 */
	public void setBlue(int blue) {
		this.blue = blue;
	}

	/**
	 * Checks if is administrator.
	 *
	 * @return true, if is administrator
	 */
	public boolean isAdministrator() {
		return administrator;
	}

	/**
	 * Sets the administrator.
	 *
	 * @param administrator the new administrator
	 */
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	/**
	 * Checks if is manage channels.
	 *
	 * @return true, if is manage channels
	 */
	public boolean isManageChannels() {
		return manageChannels;
	}

	/**
	 * Sets the manage channels.
	 *
	 * @param manageChannels the new manage channels
	 */
	public void setManageChannels(boolean manageChannels) {
		this.manageChannels = manageChannels;
	}

	/**
	 * Checks if is manage permissions.
	 *
	 * @return true, if is manage permissions
	 */
	public boolean isManagePermissions() {
		return managePermissions;
	}

	/**
	 * Sets the manage permissions.
	 *
	 * @param managePermissions the new manage permissions
	 */
	public void setManagePermissions(boolean managePermissions) {
		this.managePermissions = managePermissions;
	}

	/**
	 * Checks if is manage roles.
	 *
	 * @return true, if is manage roles
	 */
	public boolean isManageRoles() {
		return manageRoles;
	}

	/**
	 * Sets the manage roles.
	 *
	 * @param manageRoles the new manage roles
	 */
	public void setManageRoles(boolean manageRoles) {
		this.manageRoles = manageRoles;
	}

	/**
	 * Checks if is manage server.
	 *
	 * @return true, if is manage server
	 */
	public boolean isManageServer() {
		return manageServer;
	}

	/**
	 * Sets the manage server.
	 *
	 * @param manageServer the new manage server
	 */
	public void setManageServer(boolean manageServer) {
		this.manageServer = manageServer;
	}

	/**
	 * Checks if is message manage.
	 *
	 * @return true, if is message manage
	 */
	public boolean isMessageManage() {
		return messageManage;
	}

	/**
	 * Sets the message manage.
	 *
	 * @param messageManage the new message manage
	 */
	public void setMessageManage(boolean messageManage) {
		this.messageManage = messageManage;
	}

	/**
	 * Checks if is message mention everyone.
	 *
	 * @return true, if is message mention everyone
	 */
	public boolean isMessageMentionEveryone() {
		return messageMentionEveryone;
	}

	/**
	 * Sets the message mention everyone.
	 *
	 * @param messageMentionEveryone the new message mention everyone
	 */
	public void setMessageMentionEveryone(boolean messageMentionEveryone) {
		this.messageMentionEveryone = messageMentionEveryone;
	}

	/**
	 * Checks if is message read.
	 *
	 * @return true, if is message read
	 */
	public boolean isMessageRead() {
		return messageRead;
	}

	/**
	 * Sets the message read.
	 *
	 * @param messageRead the new message read
	 */
	public void setMessageRead(boolean messageRead) {
		this.messageRead = messageRead;
	}

	/**
	 * Checks if is message write.
	 *
	 * @return true, if is message write
	 */
	public boolean isMessageWrite() {
		return messageWrite;
	}

	/**
	 * Sets the message write.
	 *
	 * @param messageWrite the new message write
	 */
	public void setMessageWrite(boolean messageWrite) {
		this.messageWrite = messageWrite;
	}

	/**
	 * Checks if is nickname change.
	 *
	 * @return true, if is nickname change
	 */
	public boolean isNicknameChange() {
		return nicknameChange;
	}

	/**
	 * Sets the nickname change.
	 *
	 * @param nicknameChange the new nickname change
	 */
	public void setNicknameChange(boolean nicknameChange) {
		this.nicknameChange = nicknameChange;
	}

	/**
	 * Checks if is nickname manage.
	 *
	 * @return true, if is nickname manage
	 */
	public boolean isNicknameManage() {
		return nicknameManage;
	}

	/**
	 * Sets the nickname manage.
	 *
	 * @param nicknameManage the new nickname manage
	 */
	public void setNicknameManage(boolean nicknameManage) {
		this.nicknameManage = nicknameManage;
	}

	/**
	 * Checks if is bot role.
	 *
	 * @return true, if is bot role
	 */
	public boolean isBotRole() {
		return botRole;
	}

	/**
	 * Sets the bot role.
	 *
	 * @param botRole the new bot role
	 */
	public void setBotRole(boolean botRole) {
		this.botRole = botRole;
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(Color color) {
		if (color == null)
			color = new Color(0, 0, 0);
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
	}

	/**
	 * Checks if is cmd fun.
	 *
	 * @return true, if is cmd fun
	 */
	public boolean isCmdFun() {
		return cmdFun;
	}

	/**
	 * Sets the cmd fun.
	 *
	 * @param cmdFun the new cmd fun
	 */
	public void setCmdFun(boolean cmdFun) {
		this.cmdFun = cmdFun;
	}

	/**
	 * Checks if is cmd util.
	 *
	 * @return true, if is cmd util
	 */
	public boolean isCmdUtil() {
		return cmdUtil;
	}

	/**
	 * Sets the cmd util.
	 *
	 * @param cmdUtil the new cmd util
	 */
	public void setCmdUtil(boolean cmdUtil) {
		this.cmdUtil = cmdUtil;
	}

	/**
	 * Checks if is cmd build.
	 *
	 * @return true, if is cmd build
	 */
	public boolean isCmdBuild() {
		return cmdBuild;
	}

	/**
	 * Sets the cmd build.
	 *
	 * @param cmdBuild the new cmd build
	 */
	public void setCmdBuild(boolean cmdBuild) {
		this.cmdBuild = cmdBuild;
	}

	/**
	 * Checks if is cmd node war.
	 *
	 * @return true, if is cmd node war
	 */
	public boolean isCmdNodeWar() {
		return cmdNodeWar;
	}

	/**
	 * Sets the cmd node war.
	 *
	 * @param cmdNodeWar the new cmd node war
	 */
	public void setCmdNodeWar(boolean cmdNodeWar) {
		this.cmdNodeWar = cmdNodeWar;
	}

	/**
	 * Checks if is cmd adm.
	 *
	 * @return true, if is cmd adm
	 */
	public boolean isCmdAdm() {
		return cmdAdm;
	}

	/**
	 * Sets the cmd adm.
	 *
	 * @param cmdAdm the new cmd adm
	 */
	public void setCmdAdm(boolean cmdAdm) {
		this.cmdAdm = cmdAdm;
	}

	/**
	 * Checks if is cmd rank.
	 *
	 * @return true, if is cmd rank
	 */
	public boolean isCmdRank() {
		return cmdRank;
	}

	/**
	 * Sets the cmd rank.
	 *
	 * @param cmdRank the new cmd rank
	 */
	public void setCmdRank(boolean cmdRank) {
		this.cmdRank = cmdRank;
	}

}
