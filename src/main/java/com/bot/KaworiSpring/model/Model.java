package com.bot.KaworiSpring.model;

import org.springframework.data.annotation.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Model.
 */
public class Model {

	/** The id. */
	@Id	
	private String id;
	
	/** The new record. */
	private boolean newRecord = true;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {		
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Checks if is new record.
	 *
	 * @return true, if is new record
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * Sets the new record.
	 *
	 * @param newRecord the new new record
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
	
	
	
}
