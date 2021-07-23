package com.bot.KaworiSpring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.bot.KaworiSpring.web.security.AuthorizationSecurity;

@RestController
public class WebAdminController {

	@Autowired
	private AuthorizationSecurity authorization;
	@Autowired
	private AdventureFameService adventureFameService;
	@Autowired
	private AutoRoleService autoRoleService;
	@Autowired
	private CanalService canalService;
	@Autowired
	private ColorBDService colorBdService;
	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private GifBDService gifBdService;
	@Autowired
	private GuildaService guildaService;
	@Autowired
	private LogService logService;
	@Autowired
	private MembroService membroService;
	@Autowired
	private NodeService nodeService;
	@Autowired
	private NodeWarService nodeWarService;
	@Autowired
	private NodeWarPresenceService nodeWarPresenceService;
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private PersonagemService personagemService;
	@Autowired
	private TagService tagService;

	@GetMapping("/admin/adventure_fame")
	public ResponseEntity<List<AdventureFame>> getAllAdventureFame(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(adventureFameService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/auto_role")
	public ResponseEntity<List<AutoRole>> getAllAutoRole(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(autoRoleService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/canal")
	public ResponseEntity<List<Canal>> getAllCanal(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(canalService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/color")
	public ResponseEntity<List<ColorBD>> getAllColor(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(colorBdService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/configuration")
	public ResponseEntity<List<Configuration>> getAllConfiguration(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(configurationService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/gif")
	public ResponseEntity<List<GifBD>> getAllGif(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(gifBdService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/guilda")
	public ResponseEntity<List<Guilda>> getAllGuilda(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(guildaService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/log")
	public ResponseEntity<List<Log>> getAllLog(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(logService.getEvents(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/membro")
	public ResponseEntity<List<Membro>> getAllMembro(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(membroService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/node")
	public ResponseEntity<List<Node>> getAllNode(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(nodeService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/nodewar")
	public ResponseEntity<List<NodeWar>> getAllNodewar(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(nodeWarService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/nodewar_presence")
	public ResponseEntity<List<NodeWarPresence>> getAllNodewarPresence(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(nodeWarPresenceService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/operator")
	public ResponseEntity<List<Operator>> getAllOperator(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(operatorService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/personagem")
	public ResponseEntity<List<Personagem>> getAllPersonagem(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(personagemService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/tag")
	public ResponseEntity<List<Tag>> getAllTag(@RequestParam(value = "id") String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/adventure_fame")
	public ResponseEntity<Page<AdventureFame>> getAdventureFamePage(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<AdventureFame> pageResponse = adventureFameService.findAll(pageable);

			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/auto_role")
	public ResponseEntity<Page<AutoRole>> getAutoRole(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<AutoRole> pageResponse = autoRoleService.findAll(pageable);

			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/canal")
	public ResponseEntity<Page<Canal>> getCanal(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<Canal> pageResponse = canalService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/color")
	public ResponseEntity<Page<ColorBD>> getColor(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<ColorBD> pageResponse = colorBdService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/configuration")
	public ResponseEntity<Page<Configuration>> getConfiguration(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<Configuration> pageResponse = configurationService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/gif")
	public ResponseEntity<Page<GifBD>> getGif(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<GifBD> pageResponse = gifBdService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/guilda")
	public ResponseEntity<Page<Guilda>> getGuilda(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<Guilda> pageResponse = guildaService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/log")
	public ResponseEntity<Page<Log>> getLog(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<Log> pageResponse = logService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/membro")
	public ResponseEntity<Page<Membro>> getMembro(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<Membro> pageResponse = membroService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/node")
	public ResponseEntity<Page<Node>> getNode(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<Node> pageResponse = nodeService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/nodewar")
	public ResponseEntity<Page<NodeWar>> getNodewar(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<NodeWar> pageResponse = nodeWarService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/nodewar_presence")
	public ResponseEntity<Page<NodeWarPresence>> getNodewarPresence(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<NodeWarPresence> pageResponse = nodeWarPresenceService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/operator")
	public ResponseEntity<Page<Operator>> getOperator(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<Operator> pageResponse = operatorService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/personagem")
	public ResponseEntity<Page<Personagem>> getPersonagem(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<Personagem> pageResponse = personagemService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin/v2/tag")
	public ResponseEntity<Page<Tag>> getTag(@RequestParam(value = "id") String id,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		if (authorization.getAuthorizationFromId(id)) {
			Pageable pageable = PageRequest.of(page, size);
			Page<Tag> pageResponse = tagService.findAll(pageable);
			return new ResponseEntity<>(pageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

}
