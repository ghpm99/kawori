/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ghpm9
 */
@Controller
public class BancoDadosController {
    
    @GetMapping("bd/guilda")
    public String guilda(){
        return "bd-guilda";
    }
    
    @GetMapping("bd/roles")
    public String roles(){
        return "bd-roles";
    }
    
    @GetMapping("bd/nodewar")
    public String nodewar(){
        return "bd-nodewar";
    }
    
    @GetMapping("bd/pessoas")
    public String pessoas(){
        return "bd-pessoas";
    }
    
}
