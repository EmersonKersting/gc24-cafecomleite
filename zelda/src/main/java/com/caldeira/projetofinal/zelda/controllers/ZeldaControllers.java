package com.caldeira.projetofinal.zelda.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.caldeira.projetofinal.zelda.services.ZeldaGatewayService;

@RestController
@RequestMapping("/zelda")
public class ZeldaController{

    private final ZeldaGatewayService zeldaGatewayService;

    public ZeldaController(ZeldaGatewayService zeldaGatewayService) {
        this.zeldaGatewayService = zeldaGatewayService;
    }
}