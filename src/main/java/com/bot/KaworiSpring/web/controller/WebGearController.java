package com.bot.KaworiSpring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.service.GearService;
import com.bot.KaworiSpring.service.GuildaService;

@RestController
public class WebGearController {

	@Autowired
	private GearService gearService;
	@Autowired
	private GuildaService guildaService;

	@Secured("USER")
	@GetMapping(path = "/gear/{id}")
	public DataResponse getGear(@PathVariable String id) {
		ArrayList<GearResponse> gears = new ArrayList<>();
		gearService.findByIdDiscord(id).forEach((gear) -> {
			Guilda guilda = guildaService.findById(gear.getId());
			gears.add(new GearResponse(gear, guilda));
		});

		DataResponse response = new DataResponse(gears);

		return response;
	}

	class DataResponse {
		List<GearResponse> gears;

		public DataResponse(List<GearResponse> gears) {
			// TODO Auto-generated constructor stub
			this.gears = gears;
		}

		public List<GearResponse> getGears() {
			return gears;
		}

		public void setGears(List<GearResponse> gears) {
			this.gears = gears;
		}

	}

	class GearResponse {

		public GearResponse(Gear gear, Guilda guild) {
			// TODO Auto-generated constructor stub
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
			this.name=gear.getPersonagem().getName();
		}

		private int ap;

		private int apAwak;

		private int dp;

		private int level;

		private String trina;

		private int score;

		private boolean gearAtivo;

		private String link;

		private String name;

		private String classe;
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

		private String guildName;

		public int getAp() {
			return ap;
		}

		public void setAp(int ap) {
			this.ap = ap;
		}

		public int getApAwak() {
			return apAwak;
		}

		public void setApAwak(int apAwak) {
			this.apAwak = apAwak;
		}

		public int getDp() {
			return dp;
		}

		public void setDp(int dp) {
			this.dp = dp;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public String getTrina() {
			return trina;
		}

		public void setTrina(String trina) {
			this.trina = trina;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public boolean isGearAtivo() {
			return gearAtivo;
		}

		public void setGearAtivo(boolean gearAtivo) {
			this.gearAtivo = gearAtivo;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getClasse() {
			return classe;
		}

		public void setClasse(String classe) {
			this.classe = classe;
		}

		public String getBattleMode() {
			return battleMode;
		}

		public void setBattleMode(String battleMode) {
			this.battleMode = battleMode;
		}

		public String getGuildName() {
			return guildName;
		}

		public void setGuildName(String guildName) {
			this.guildName = guildName;
		}

	}

}
