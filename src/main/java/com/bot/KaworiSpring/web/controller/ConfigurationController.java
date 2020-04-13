package com.bot.KaworiSpring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigurationController {

    @GetMapping("config/nodewar")
    public String nodewar() {
        return "config-nodewar";
    }

    @GetMapping("config/fama")
    public String fama() {
        return "config-fama";
    }

    @GetMapping("config/color")
    public String color() {
        return "config-color";
    }

    @GetMapping("config/node")
    public String node() {
        return "config-node";
    }

    @GetMapping("config/configuracao")
    public String configuracao() {
        return "config-config";
    }

}
