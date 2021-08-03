package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;


// TODO: Auto-generated Javadoc
/**
 * The Class GifBD.
 */
@Document
public class GifBD extends Model{
	
	/** The url. */
	private String url;
	
	/** The type. */
	private String type;

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

}
