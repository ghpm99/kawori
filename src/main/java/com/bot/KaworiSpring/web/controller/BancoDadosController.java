/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.web.controller;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.NodeWarService;
import com.bot.KaworiSpring.service.OperatorService;
import com.bot.KaworiSpring.service.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ghpm9
 */
@Controller
public class BancoDadosController {

    @Autowired
    private GuildaService guildaService;
    @Autowired
    private NodeWarService nodeWarService;
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private TagService tagService;

    @GetMapping("bd/guilda")
    public String guilda(Model model) {
        List<Guilda> guilds = guildaService.findAll();
        model.addAttribute("guildas", guilds);
        return "bd-guilda";
    }

    @GetMapping("bd/roles")
    public String roles(Model model) {
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);
        return "bd-roles";
    }

    @ResponseBody
    @GetMapping(value = "bd/roles/find")
    public Page<Tag> findRole(@RequestParam("page") int pag, @RequestParam("size") int size, Model mode) {
        Page<Tag> tags = tagService.findAll(PageRequest.of(pag, size));        
        return tags;
    }

    @GetMapping("bd/nodewar")
    public String nodewar(Model model) {
        List<NodeWar> nodes = nodeWarService.findAll();
        model.addAttribute("nodes", nodes);
        return "bd-nodewar";
    }

    @GetMapping("bd/pessoas")
    public String pessoas(Model model) {
        List<Operator> users = operatorService.findAll();
        model.addAttribute("users", users);
        return "bd-pessoas";
    }

}
