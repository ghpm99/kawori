package com.bot.KaworiSpring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Operator;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

// TODO: Auto-generated Javadoc
/**
 * The Class LanguageService.
 */
@Service
public class LanguageService {

	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;

	/** The operator service. */
	@Autowired
	private OperatorService operatorService;

	private final String DEFAULT_LANGUAGE = "Brazil";

	/**
	 * Gets the language.
	 *
	 * @param guild the guild
	 * @param user  the user
	 * @return the language
	 */
	public String getLanguage(Guild guild, User user) {
		Guilda guilda = guildaService.findById(guild.getId());
		Operator operator = operatorService.findById(user.getId());
		return getLanguage(guilda, operator);
	}

	/**
	 * Gets the language.
	 *
	 * @param guild the guild
	 * @param user  the user
	 * @return the language
	 */
	private String getLanguage(Guilda guild, Operator user) {
		String language = DEFAULT_LANGUAGE;
		if (user.getRegion() != null) {
			language = user.getRegion();
		} else if (guild.getRegion() != null) {
			language = guild.getRegion();
		}
		return language;
	}

	/**
	 * Load message.
	 *
	 * @param guild   the guild
	 * @param user    the user
	 * @param message the message
	 * @return the string
	 */
	public String loadMessage(Guild guild, User user, String message) {
		String region = getLanguage(guild, user);
		String fileName = System.getProperty("user.dir") + File.separator + "language" + File.separator + region
				+ ".lng";

		String retorno = null;
		Properties pro = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(fileName);
			pro.load(input);
			retorno = pro.getProperty(message);
		} catch (FileNotFoundException e) {
			createFile(System.getProperty("user.dir") + File.separator + "language", region + ".lng");
			return " _nameMention , that language is not yet supported!";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return " _nameMention , that language is not yet supported!";
		}
		if (retorno == null) {
			retorno = message;
		}
		return retorno;
	}

	/**
	 * Creates the file.
	 *
	 * @param path     the path
	 * @param fileName the file name
	 */
	private void createFile(String path, String fileName) {
		File newDirectory = new File(path);
		File newFile = new File(path + File.separator + fileName);

		if (newDirectory.mkdirs()) {
			System.out.println("New Directory " + newDirectory.getAbsolutePath() + " was successfully created.");
		} else {
			System.out.println("New Directory " + newDirectory.getAbsolutePath() + " was failed to be created.");
		}

		try {
			if (newFile.createNewFile()) {
				System.out.println("New file " + newFile.getAbsolutePath() + " was successfully created.");
			} else {
				System.out.println("New file " + newFile.getAbsolutePath() + " was failed to be created.");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Sets the region.
	 *
	 * @param guild  the guild
	 * @param region the region
	 */
	public void setRegion(Guild guild, String region) {
		Guilda guilda = guildaService.findById(guild.getId());
		guilda.setRegion(region.toLowerCase());
		guildaService.save(guilda);

	}

	/**
	 * Sets the region.
	 *
	 * @param user   the user
	 * @param region the region
	 */
	public void setRegion(User user, String region) {

		Operator operator = operatorService.findById(user.getId());
		operator.setRegion(region.toLowerCase());
		operatorService.save(operator);

	}

}
