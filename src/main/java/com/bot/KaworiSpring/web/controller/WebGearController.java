package com.bot.KaworiSpring.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.service.GearService;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.PersonagemService;
import com.bot.KaworiSpring.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: Auto-generated Javadoc
/**
 * The Class WebGearController.
 */
@RestController
@RequestMapping("/gear")
public class WebGearController {

	/** The gear service. */
	@Autowired
	private GearService gearService;
	
	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;
	
	/** The personagem service. */
	@Autowired
	private PersonagemService personagemService;
	
	/**
	 * Gets the gear.
	 *
	 * @param id the id
	 * @return the gear
	 */
	@Secured("USER")
	@GetMapping("/gearsByIdUser")
	public ResponseEntity<DataResponse> getGear(@RequestParam(value = "id") String id) {
		
			ArrayList<GearResponse> gears = new ArrayList<>();
			gearService.findByIdDiscord(id).forEach((gear) -> {
				Guilda guilda = guildaService.findById(gear.getIdGuild());
				gears.add(new GearResponse(gear, guilda));
			});

			DataResponse response = new DataResponse(gears);

			return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

	/**
	 * Delete gear.
	 *
	 * @param idUser the id user
	 * @param idGear the id gear
	 */
	@Secured("USER")
	@DeleteMapping("/gear")
	public void deleteGear(@RequestParam(value = "idUser") String idUser,
			@RequestParam(value = "idGear") String idGear) {
		System.out.println("User:" + idUser + " Gear:" + idGear);

	}

	/**
	 * Gets the gear by id.
	 *
	 * @param id the id
	 * @return the gear by id
	 */
	@Secured("USER")
	@GetMapping("/gearById")
	public ResponseEntity<GearResponse> getGearById(@RequestParam(value = "idGear") String id) {
		
			Gear gear = gearService.findById(id);
			Guilda guild = guildaService.findById(gear.getIdGuild());
			GearResponse response = new GearResponse(gear, guild);
			return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

	/**
	 * Update gear.
	 *
	 * @param idUser the id user
	 * @param idGear the id gear
	 * @param characterName the character name
	 * @param classe the classe
	 * @param battleMode the battle mode
	 * @param level the level
	 * @param ap the ap
	 * @param apawak the apawak
	 * @param dp the dp
	 * @param trina the trina
	 * @return the response entity
	 */
	@Secured("USER")
	@PutMapping("/gear")
	public ResponseEntity<GearResponse> updateGear(@RequestParam(value = "idUser") String idUser,
			@RequestParam(value = "idGear") String idGear, @RequestParam(value = "characterName") String characterName,
			@RequestParam(value = "class") String classe, @RequestParam(value = "battleMode") String battleMode,
			@RequestParam(value = "level") int level, @RequestParam(value = "ap") int ap,
			@RequestParam(value = "apawak") int apawak, @RequestParam(value = "dp") int dp,
			@RequestParam(value = "trina") String trina) {
		
		
			Gear gear = gearService.findById(idGear);
			gear.getPersonagem().setName(characterName);
			gear.getPersonagem().setClasse(classe);
			gear.getPersonagem().setBattleMode(battleMode);
			gear.setLevel(level);
			gear.setAp(ap);
			gear.setApAwak(apawak);
			gear.setDp(dp);
			gear.setTrina(trina);
			gear.setScore(Util.calculateGearScore(gear.getAp(), gear.getApAwak(), gear.getDp()));

			personagemService.save(gear.getPersonagem());
			gearService.save(gear);

			GearResponse response = new GearResponse(gear, guildaService.findById(gear.getIdGuild()));
			return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

	/**
	 * The Class DataResponse.
	 */
	class DataResponse {
		
		/** The gears. */
		List<GearResponse> gears;

		/**
		 * Instantiates a new data response.
		 *
		 * @param gears the gears
		 */
		public DataResponse(List<GearResponse> gears) {
			// TODO Auto-generated constructor stub
			this.gears = gears;
		}

		/**
		 * Gets the gears.
		 *
		 * @return the gears
		 */
		public List<GearResponse> getGears() {
			return gears;
		}

		/**
		 * Sets the gears.
		 *
		 * @param gears the new gears
		 */
		public void setGears(List<GearResponse> gears) {
			this.gears = gears;
		}

	}

	/**
	 * The Class GearResponse.
	 */
	class GearResponse {

		/**
		 * Instantiates a new gear response.
		 *
		 * @param gear the gear
		 * @param guild the guild
		 */
		public GearResponse(Gear gear, Guilda guild) {
			// TODO Auto-generated constructor stub
			this.id = gear.getId();
			this.ap = gear.getAp();
			this.apAwak = gear.getApAwak();
			this.dp = gear.getDp();
			this.level = gear.getLevel();
			this.trina = gear.getTrina();
			this.score = gear.getScore();
			this.gearAtivo = gear.isAtivo();
			this.link = gear.getLink();
			this.classe = gear.getPersonagem().getClasse();
			this.battleMode = gear.getPersonagem().getBattleMode();
			this.guildName = guild.getName();
			this.name = gear.getPersonagem().getName();
		}

		/** The id. */
		private String id;

		/** The ap. */
		private int ap;

		/** The ap awak. */
		private int apAwak;

		/** The dp. */
		private int dp;

		/** The level. */
		private int level;

		/** The trina. */
		private String trina;

		/** The score. */
		private int score;

		/** The gear ativo. */
		private boolean gearAtivo;

		/** The link. */
		private String link;

		/** The name. */
		private String name;

		/** The classe. */
		private String classe;
		
		/** The battle mode. */
		/*
		 * 1 = warrior 2 = witch 3 = kunoichi 4 = dark knight 5 = lahn 6 = shai 7 =
		 * wizard 8 = archer 9 = mystic 10 = maehwa 11 = sorceress 12 = tamer 13 =
		 * ranger 14 = striker 15 = valkyrie 16 = berserker 17 = musah 18 = guardian 19
		 * = ninja
		 */
		private String battleMode;
		/*
		 * 1 = awak 2 = succ
		 */

		/** The guild name. */
		private String guildName;

		/**
		 * Gets the ap.
		 *
		 * @return the ap
		 */
		public int getAp() {
			return ap;
		}

		/**
		 * Sets the ap.
		 *
		 * @param ap the new ap
		 */
		public void setAp(int ap) {
			this.ap = ap;
		}

		/**
		 * Gets the ap awak.
		 *
		 * @return the ap awak
		 */
		public int getApAwak() {
			return apAwak;
		}

		/**
		 * Sets the ap awak.
		 *
		 * @param apAwak the new ap awak
		 */
		public void setApAwak(int apAwak) {
			this.apAwak = apAwak;
		}

		/**
		 * Gets the dp.
		 *
		 * @return the dp
		 */
		public int getDp() {
			return dp;
		}

		/**
		 * Sets the dp.
		 *
		 * @param dp the new dp
		 */
		public void setDp(int dp) {
			this.dp = dp;
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
		 * Gets the trina.
		 *
		 * @return the trina
		 */
		public String getTrina() {
			return trina;
		}

		/**
		 * Sets the trina.
		 *
		 * @param trina the new trina
		 */
		public void setTrina(String trina) {
			this.trina = trina;
		}

		/**
		 * Gets the score.
		 *
		 * @return the score
		 */
		public int getScore() {
			return score;
		}

		/**
		 * Sets the score.
		 *
		 * @param score the new score
		 */
		public void setScore(int score) {
			this.score = score;
		}

		/**
		 * Checks if is gear ativo.
		 *
		 * @return true, if is gear ativo
		 */
		public boolean isGearAtivo() {
			return gearAtivo;
		}

		/**
		 * Sets the gear ativo.
		 *
		 * @param gearAtivo the new gear ativo
		 */
		public void setGearAtivo(boolean gearAtivo) {
			this.gearAtivo = gearAtivo;
		}

		/**
		 * Gets the link.
		 *
		 * @return the link
		 */
		public String getLink() {
			return link;
		}

		/**
		 * Sets the link.
		 *
		 * @param link the new link
		 */
		public void setLink(String link) {
			this.link = link;
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
		 * Gets the classe.
		 *
		 * @return the classe
		 */
		public String getClasse() {
			return classe;
		}

		/**
		 * Sets the classe.
		 *
		 * @param classe the new classe
		 */
		public void setClasse(String classe) {
			this.classe = classe;
		}

		/**
		 * Gets the battle mode.
		 *
		 * @return the battle mode
		 */
		public String getBattleMode() {
			return battleMode;
		}

		/**
		 * Sets the battle mode.
		 *
		 * @param battleMode the new battle mode
		 */
		public void setBattleMode(String battleMode) {
			this.battleMode = battleMode;
		}

		/**
		 * Gets the guild name.
		 *
		 * @return the guild name
		 */
		public String getGuildName() {
			return guildName;
		}

		/**
		 * Sets the guild name.
		 *
		 * @param guildName the new guild name
		 */
		public void setGuildName(String guildName) {
			this.guildName = guildName;
		}

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

	}

}
