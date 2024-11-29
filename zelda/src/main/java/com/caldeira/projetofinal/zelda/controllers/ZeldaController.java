package com.caldeira.projetofinal.zelda.controllers;

@RestController
@RequestMapping("/zelda")
public class ZeldaController{
    
    private final ZeldaGatewayService zeldaGatewayService;

    public ZeldaController(ZeldaGatewayService zeldaGatewayService) {
        this.zeldaGatewayService = zeldaGatewayService;
    }

}