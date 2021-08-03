package com.bot.KaworiSpring.web.controller;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/v2/admin")
public class WebAdminControllerV2 {

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
	 * Gets the adventure fame page.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the adventure fame page
	 */
	@GetMapping("/adventure_fame")
	public ResponseEntity<Page<AdventureFame>> getAdventureFamePage(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<AdventureFame> pageResponse = adventureFameService.findAll(pageable);

		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the auto role.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the auto role
	 */
	@GetMapping("/auto_role")
	public ResponseEntity<Page<AutoRole>> getAutoRole(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<AutoRole> pageResponse = autoRoleService.findAll(pageable);

		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the canal.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the canal
	 */
	@GetMapping("/canal")
	public ResponseEntity<Page<Canal>> getCanal(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Canal> pageResponse = canalService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the color.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the color
	 */
	@GetMapping("/color")
	public ResponseEntity<Page<ColorBD>> getColor(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<ColorBD> pageResponse = colorBdService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the configuration.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the configuration
	 */
	@GetMapping("/configuration")
	public ResponseEntity<Page<Configuration>> getConfiguration(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Configuration> pageResponse = configurationService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the gif.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the gif
	 */
	@GetMapping("/gif")
	public ResponseEntity<Page<GifBD>> getGif(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<GifBD> pageResponse = gifBdService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the guilda.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the guilda
	 */
	@GetMapping("/guilda")
	public ResponseEntity<Page<Guilda>> getGuilda(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Guilda> pageResponse = guildaService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the log.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the log
	 */
	@GetMapping("/log")
	public ResponseEntity<Page<Log>> getLog(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Log> pageResponse = logService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the membro.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the membro
	 */
	@GetMapping("/membro")
	public ResponseEntity<Page<Membro>> getMembro(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Membro> pageResponse = membroService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the node.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the node
	 */
	@GetMapping("/node")
	public ResponseEntity<Page<Node>> getNode(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Node> pageResponse = nodeService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the nodewar.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the nodewar
	 */
	@GetMapping("/nodewar")
	public ResponseEntity<Page<NodeWar>> getNodewar(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<NodeWar> pageResponse = nodeWarService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the nodewar presence.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the nodewar presence
	 */
	@GetMapping("/nodewar_presence")
	public ResponseEntity<Page<NodeWarPresence>> getNodewarPresence(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<NodeWarPresence> pageResponse = nodeWarPresenceService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the operator.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the operator
	 */
	@GetMapping("/operator")
	public ResponseEntity<Page<Operator>> getOperator(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Operator> pageResponse = operatorService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the personagem.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the personagem
	 */
	@GetMapping("/personagem")
	public ResponseEntity<Page<Personagem>> getPersonagem(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Personagem> pageResponse = personagemService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

	/**
	 * Gets the tag.
	 *
	 * @param id   the id
	 * @param page the page
	 * @param size the size
	 * @return the tag
	 */
	@GetMapping("/tag")
	public ResponseEntity<Page<Tag>> getTag(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Tag> pageResponse = tagService.findAll(pageable);
		return new ResponseEntity<>(pageResponse, HttpStatus.OK);

	}

}
