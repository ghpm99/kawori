package com.bot.KaworiSpring.web.controller;

import java.util.List;

import com.bot.KaworiSpring.model.AdventureFame;
import com.bot.KaworiSpring.model.AutoRole;
import com.bot.KaworiSpring.model.Canal;
import com.bot.KaworiSpring.model.ColorBD;
import com.bot.KaworiSpring.model.Configuration;
import com.bot.KaworiSpring.model.GifBD;
import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.model.NodeWarPresence;
import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.AdventureFameService;
import com.bot.KaworiSpring.service.AutoRoleService;
import com.bot.KaworiSpring.service.CanalService;
import com.bot.KaworiSpring.service.ColorBDService;
import com.bot.KaworiSpring.service.ConfigurationService;
import com.bot.KaworiSpring.service.GifBDService;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.LogService;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.service.NodeService;
import com.bot.KaworiSpring.service.NodeWarPresenceService;
import com.bot.KaworiSpring.service.NodeWarService;
import com.bot.KaworiSpring.service.OperatorService;
import com.bot.KaworiSpring.service.PersonagemService;
import com.bot.KaworiSpring.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: Auto-generated Javadoc
/**
 * The Class WebAdminController.
 */
@RestController
@RequestMapping("/admin")
public class WebAdminController {

	/** The adventure fame service. */
	@Autowired
	private AdventureFameService adventureFameService;

	/** The auto role service. */
	@Autowired
	private AutoRoleService autoRoleService;

	/** The canal service. */
	@Autowired
	private CanalService canalService;

	/** The color bd service. */
	@Autowired
	private ColorBDService colorBdService;

	/** The configuration service. */
	@Autowired
	private ConfigurationService configurationService;

	/** The gif bd service. */
	@Autowired
	private GifBDService gifBdService;

	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;

	/** The log service. */
	@Autowired
	private LogService logService;

	/** The membro service. */
	@Autowired
	private MembroService membroService;

	/** The node service. */
	@Autowired
	private NodeService nodeService;

	/** The node war service. */
	@Autowired
	private NodeWarService nodeWarService;

	/** The node war presence service. */
	@Autowired
	private NodeWarPresenceService nodeWarPresenceService;

	/** The operator service. */
	@Autowired
	private OperatorService operatorService;

	/** The personagem service. */
	@Autowired
	private PersonagemService personagemService;

	/** The tag service. */
	@Autowired
	private TagService tagService;

	/**
	 * Gets the all adventure fame.
	 *
	 * @param id the id
	 * @return the all adventure fame
	 */
	@GetMapping("/adventure_fame")
	public ResponseEntity<List<AdventureFame>> getAllAdventureFame(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(adventureFameService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all auto role.
	 *
	 * @param id the id
	 * @return the all auto role
	 */
	@GetMapping("/auto_role")
	public ResponseEntity<List<AutoRole>> getAllAutoRole(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(autoRoleService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all canal.
	 *
	 * @param id the id
	 * @return the all canal
	 */
	@GetMapping("/canal")
	public ResponseEntity<List<Canal>> getAllCanal(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(canalService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all color.
	 *
	 * @param id the id
	 * @return the all color
	 */
	@GetMapping("/color")
	public ResponseEntity<List<ColorBD>> getAllColor(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(colorBdService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all configuration.
	 *
	 * @param id the id
	 * @return the all configuration
	 */
	@GetMapping("/configuration")
	public ResponseEntity<List<Configuration>> getAllConfiguration(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(configurationService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all gif.
	 *
	 * @param id the id
	 * @return the all gif
	 */
	@GetMapping("/gif")
	public ResponseEntity<List<GifBD>> getAllGif(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(gifBdService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all guilda.
	 *
	 * @param id the id
	 * @return the all guilda
	 */
	@GetMapping("/guilda")
	public ResponseEntity<List<Guilda>> getAllGuilda(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(guildaService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all log.
	 *
	 * @param id the id
	 * @return the all log
	 */
	@GetMapping("/log")
	public ResponseEntity<List<Log>> getAllLog(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(logService.getEvents(), HttpStatus.OK);

	}

	/**
	 * Gets the all membro.
	 *
	 * @param id the id
	 * @return the all membro
	 */
	@GetMapping("/membro")
	public ResponseEntity<List<Membro>> getAllMembro(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(membroService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all node.
	 *
	 * @param id the id
	 * @return the all node
	 */
	@GetMapping("/node")
	public ResponseEntity<List<Node>> getAllNode(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(nodeService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all nodewar.
	 *
	 * @param id the id
	 * @return the all nodewar
	 */
	@GetMapping("/nodewar")
	public ResponseEntity<List<NodeWar>> getAllNodewar(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(nodeWarService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all nodewar presence.
	 *
	 * @param id the id
	 * @return the all nodewar presence
	 */
	@GetMapping("/nodewar_presence")
	public ResponseEntity<List<NodeWarPresence>> getAllNodewarPresence(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(nodeWarPresenceService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all operator.
	 *
	 * @param id the id
	 * @return the all operator
	 */
	@GetMapping("/operator")
	public ResponseEntity<List<Operator>> getAllOperator(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(operatorService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all personagem.
	 *
	 * @param id the id
	 * @return the all personagem
	 */
	@GetMapping("/personagem")
	public ResponseEntity<List<Personagem>> getAllPersonagem(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(personagemService.findAll(), HttpStatus.OK);

	}

	/**
	 * Gets the all tag.
	 *
	 * @param id the id
	 * @return the all tag
	 */
	@GetMapping("/tag")
	public ResponseEntity<List<Tag>> getAllTag(@RequestParam(value = "id") String id) {

		return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);

	}
}
