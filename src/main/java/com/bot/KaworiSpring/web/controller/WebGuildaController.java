package com.bot.KaworiSpring.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.web.security.AuthorizationSecurity;

@RestController
public class WebGuildaController {

	@Autowired
	private MembroService membroService;
	@Autowired
	private GuildaService guildaService;
	@Autowired
	private AuthorizationSecurity authorization;

	@Secured("USER")
	@GetMapping(path = "/guilds/{id}")
	public ResponseEntity<DataResponse> getGuilds(@PathVariable String id) {
		if (authorization.getAuthorizationFromId(id)) {
			ArrayList<MembroResponse> membros = new ArrayList<>();

			membroService.findById(id).forEach((membro) -> {
				Guilda guild = guildaService.findById(membro.getIdGuild());
				MembroResponse respoMembro = new MembroResponse(membro, guild);
				membros.add(respoMembro);
			});

			DataResponse response = new DataResponse(membros);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	class DataResponse {

		List<MembroResponse> membros;

		public DataResponse(List<MembroResponse> membros) {
			this.membros = membros;
		}

		public List<MembroResponse> getMembros() {
			return membros;
		}

		public void setMembros(List<MembroResponse> membros) {
			this.membros = membros;
		}

	}

	class MembroResponse {
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

		private GuildResponse guild;

		private String nick;

		private String familyName;

		private boolean banned;

		private boolean gear;

		private Date gearUpdate;

		private boolean hero;

		private boolean visitor;

		private boolean novice;

		public GuildResponse getGuild() {
			return guild;
		}

		public void setGuild(GuildResponse guild) {
			this.guild = guild;
		}

		public String getNick() {
			return nick;
		}

		public void setNick(String nick) {
			this.nick = nick;
		}

		public String getFamilyName() {
			return familyName;
		}

		public void setFamilyName(String familyName) {
			this.familyName = familyName;
		}

		public boolean isBanned() {
			return banned;
		}

		public void setBanned(boolean banned) {
			this.banned = banned;
		}

		public boolean isGear() {
			return gear;
		}

		public void setGear(boolean gear) {
			this.gear = gear;
		}

		public Date getGearUpdate() {
			return gearUpdate;
		}

		public void setGearUpdate(Date gearUpdate) {
			this.gearUpdate = gearUpdate;
		}

		public boolean isHero() {
			return hero;
		}

		public void setHero(boolean hero) {
			this.hero = hero;
		}

		public boolean isVisitor() {
			return visitor;
		}

		public void setVisitor(boolean visitor) {
			this.visitor = visitor;
		}

		public boolean isNovice() {
			return novice;
		}

		public void setNovice(boolean novice) {
			this.novice = novice;
		}

	}

	class GuildResponse {

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

		private String name;

		private boolean active;

		private String region;

		private boolean block;

		private String defaultWelcomeMessage;

		private int cmdCount;

		private int level;

		private int exp;

		private int expRequired;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public boolean isBlock() {
			return block;
		}

		public void setBlock(boolean block) {
			this.block = block;
		}

		public String getDefaultWelcomeMessage() {
			return defaultWelcomeMessage;
		}

		public void setDefaultWelcomeMessage(String defaultWelcomeMessage) {
			this.defaultWelcomeMessage = defaultWelcomeMessage;
		}

		public int getCmdCount() {
			return cmdCount;
		}

		public void setCmdCount(int cmdCount) {
			this.cmdCount = cmdCount;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getExp() {
			return exp;
		}

		public void setExp(int exp) {
			this.exp = exp;
		}

		public int getExpRequired() {
			return expRequired;
		}

		public void setExpRequired(int expRequired) {
			this.expRequired = expRequired;
		}

	}

}
