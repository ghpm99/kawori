package com.bot.KaworiSpring.web.controller;


import com.bot.KaworiSpring.model.AdventureFame;
import com.bot.KaworiSpring.model.ColorBD;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.service.AdventureFameService;
import com.bot.KaworiSpring.service.ColorBDService;
import com.bot.KaworiSpring.service.NodeService;
import com.bot.KaworiSpring.service.NodeWarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigurationController {

    @Autowired
    private NodeWarService nodeWarService;
    @Autowired
    private AdventureFameService adventureFameService;
    @Autowired
    private ColorBDService colorService;
    @Autowired
    private NodeService nodeService;
    
    @GetMapping("config/nodewar")
    public String nodewar(Model model) {
        List<NodeWar> nodes = nodeWarService.findAll();
        model.addAttribute("nodes", nodes);
        return "config-nodewar";
    }

    @GetMapping("config/fama")
    public String fama(Model model) {
        List<AdventureFame> fames = adventureFameService.findAll();
        model.addAttribute("fames", fames);
        return "config-fama";
    }

    @GetMapping("config/color")
    public String color(Model model) {
        List<ColorBD> colors = colorService.findAll();
        model.addAttribute("colors", colors);
        return "config-color";
    }

    @GetMapping("config/node")
    public String node(Model model) {
        List<Node> nodes = nodeService.findAll();
        model.addAttribute("nodes", nodes);
        return "config-node";
    }

    @GetMapping("config/configuracao")
    public String configuracao(Model model) {
        return "config-config";
    }

}
