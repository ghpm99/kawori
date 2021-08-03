package com.bot.KaworiSpring.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.MembroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: Auto-generated Javadoc
/**
 * The Class WebGuildaController.
 */
@RestController
@RequestMapping("/guilds")
public class WebGuildaController {

	/** The membro service. */
	@Autowired
	private MembroService membroService;
	
	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;
	
	/**
	 * Gets the guilds.
	 *
	 * @param id the id
	 * @return the guilds
	 */
	@Secured("USER")
	@GetMapping(path = "/{id}")
	public ResponseEntity<DataResponse> getGuilds(@PathVariable String id) {
		
			ArrayList<MembroResponse> membros = new ArrayList<>();

			membroService.findById(id).forEach((membro) -> {
				Guilda guild = guildaService.findById(membro.getIdGuild());
				MembroResponse respoMembro = new MembroResponse(membro, guild);
				membros.add(respoMembro);
			});

			DataResponse response = new DataResponse(membros);

			return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

	/**
	 * The Class DataResponse.
	 */
	class DataResponse {

		/** The membros. */
		List<MembroResponse> membros;

		/**
		 * Instantiates a new data response.
		 *
		 * @param membros the membros
		 */
		public DataResponse(List<MembroResponse> membros) {
			this.membros = membros;
		}

		/**
		 * Gets the membros.
		 *
		 * @return the membros
		 */
		public List<MembroResponse> getMembros() {
			return membros;
		}

		/**
		 * Sets the membros.
		 *
		 * @param membros the new membros
		 */
		public void setMembros(List<MembroResponse> membros) {
			this.membros = membros;
		}

	}

	/**
	 * The Class MembroResponse.
	 */
	class MembroResponse {
		
		/**
		 * Instantiates a new membro response.
		 *
		 * @param membro the membro
		 * @param guilda the guilda
		 */
		public MembroResponse(Membro membro, Guilda guilda) {
			// TODO Auto-generated constructor stub

			// TODO Auto-generated constructor stub
			this.nick = membro.getNick();
			this.familyName = membro.getFamilyName();
			this.banned = membro.isBanned();
			this.gear = membro.isGear();
			this.gearUpdate = membro.getGearUpdate();
			this.hero = membro.isHero();
			this.visitor = membro.isVisitor();
			this.novice = membro.isNovice();
			this.guild = new GuildResponse(guilda);
		}

		/** The guild. */
		private GuildResponse guild;

		/** The nick. */
		private String nick;

		/** The family name. */
		private String familyName;

		/** The banned. */
		private boolean banned;

		/** The gear. */
		private boolean gear;

		/** The gear update. */
		private Date gearUpdate;

		/** The hero. */
		private boolean hero;

		/** The visitor. */
		private boolean visitor;

		/** The novice. */
		private boolean novice;

		/**
		 * Gets the guild.
		 *
		 * @return the guild
		 */
		public GuildResponse getGuild() {
			return guild;
		}

		/**
		 * Sets the guild.
		 *
		 * @param guild the new guild
		 */
		public void setGuild(GuildResponse guild) {
			this.guild = guild;
		}

		/**
		 * Gets the nick.
		 *
		 * @return the nick
		 */
		public String getNick() {
			return nick;
		}

		/**
		 * Sets the nick.
		 *
		 * @param nick the new nick
		 */
		public void setNick(String nick) {
			this.nick = nick;
		}

		/**
		 * Gets the family name.
		 *
		 * @return the family name
		 */
		public String getFamilyName() {
			return familyName;
		}

		/**
		 * Sets the family name.
		 *
		 * @param familyName the new family name
		 */
		public void setFamilyName(String familyName) {
			this.familyName = familyName;
		}

		/**
		 * Checks if is banned.
		 *
		 * @return true, if is banned
		 */
		public boolean isBanned() {
			return banned;
		}

		/**
		 * Sets the banned.
		 *
		 * @param banned the new banned
		 */
		public void setBanned(boolean banned) {
			this.banned = banned;
		}

		/**
		 * Checks if is gear.
		 *
		 * @return true, if is gear
		 */
		public boolean isGear() {
			return gear;
		}

		/**
		 * Sets the gear.
		 *
		 * @param gear the new gear
		 */
		public void setGear(boolean gear) {
			this.gear = gear;
		}

		/**
		 * Gets the gear update.
		 *
		 * @return the gear update
		 */
		public Date getGearUpdate() {
			return gearUpdate;
		}

		/**
		 * Sets the gear update.
		 *
		 * @param gearUpdate the new gear update
		 */
		public void setGearUpdate(Date gearUpdate) {
			this.gearUpdate = gearUpdate;
		}

		/**
		 * Checks if is hero.
		 *
		 * @return true, if is hero
		 */
		public boolean isHero() {
			return hero;
		}

		/**
		 * Sets the hero.
		 *
		 * @param hero the new hero
		 */
		public void setHero(boolean hero) {
			this.hero = hero;
		}

		/**
		 * Checks if is visitor.
		 *
		 * @return true, if is visitor
		 */
		public boolean isVisitor() {
			return visitor;
		}

		/**
		 * Sets the visitor.
		 *
		 * @param visitor the new visitor
		 */
		public void setVisitor(boolean visitor) {
			this.visitor = visitor;
		}

		/**
		 * Checks if is novice.
		 *
		 * @return true, if is novice
		 */
		public boolean isNovice() {
			return novice;
		}

		/**
		 * Sets the novice.
		 *
		 * @param novice the new novice
		 */
		public void setNovice(boolean novice) {
			this.novice = novice;
		}

	}

	/**
	 * The Class GuildResponse.
	 */
	class GuildResponse {

		/**
		 * Instantiates a new guild response.
		 *
		 * @param guilda the guilda
		 */
		public GuildResponse(Guilda guilda) {
			// TODO Auto-generated constructor stub
			this.name = guilda.getName();
			this.active = guilda.isActive();
			this.region = guilda.getRegion();
			this.block = guilda.isBlock();
			this.defaultWelcomeMessage = guilda.getDefaultWelcomeMessage();
			this.cmdCount = guilda.getCmdCount();
			this.level = guilda.getLevel();
			this.exp = guilda.getExp();
			this.expRequired = guilda.getExpRequired();
		}

		/** The name. */
		private String name;

		/** The active. */
		private boolean active;

		/** The region. */
		private String region;

		/** The block. */
		private boolean block;

		/** The default welcome message. */
		private String defaultWelcomeMessage;

		/** The cmd count. */
		private int cmdCount;

		/** The level. */
		private int level;

		/** The exp. */
		private int exp;

		/** The exp required. */
		private int expRequired;

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
		 * Gets the region.
		 *
		 * @return the region
		 */
		public String getRegion() {
			return region;
		}

		/**
		 * Sets the region.
		 *
		 * @param region the new region
		 */
		public void setRegion(String region) {
			this.region = region;
		}

		/**
		 * Checks if is block.
		 *
		 * @return true, if is block
		 */
		public boolean isBlock() {
			return block;
		}

		/**
		 * Sets the block.
		 *
		 * @param block the new block
		 */
		public void setBlock(boolean block) {
			this.block = block;
		}

		/**
		 * Gets the default welcome message.
		 *
		 * @return the default welcome message
		 */
		public String getDefaultWelcomeMessage() {
			return defaultWelcomeMessage;
		}

		/**
		 * Sets the default welcome message.
		 *
		 * @param defaultWelcomeMessage the new default welcome message
		 */
		public void setDefaultWelcomeMessage(String defaultWelcomeMessage) {
			this.defaultWelcomeMessage = defaultWelcomeMessage;
		}

		/**
		 * Gets the cmd count.
		 *
		 * @return the cmd count
		 */
		public int getCmdCount() {
			return cmdCount;
		}

		/**
		 * Sets the cmd count.
		 *
		 * @param cmdCount the new cmd count
		 */
		public void setCmdCount(int cmdCount) {
			this.cmdCount = cmdCount;
		}

		/**
		 * Gets the level.
		 *
		 * @return the level
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * Sets the level.
		 *
		 * @param level the new level
		 */
		public void setLevel(int level) {
			this.level = level;
		}

		/**
		 * Gets the exp.
		 *
		 * @return the exp
		 */
		public int getExp() {
			return exp;
		}

		/**
		 * Sets the exp.
		 *
		 * @param exp the new exp
		 */
		public void setExp(int exp) {
			this.exp = exp;
		}

		/**
		 * Gets the exp required.
		 *
		 * @return the exp required
		 */
		public int getExpRequired() {
			return expRequired;
		}

		/**
		 * Sets the exp required.
		 *
		 * @param expRequired the new exp required
		 */
		public void setExpRequired(int expRequired) {
			this.expRequired = expRequired;
		}

	}

}
