package com.caldeira.projetofinal.zelda.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/zelda")
public class ZeldaController{
    
    private final ZeldaGatewayService zeldaGatewayService;

    public ZeldaController(ZeldaGatewayService zeldaGatewayService) {
        this.zeldaGatewayService = zeldaGatewayService;
    }

}