package com.caldeira.projetofinal.zelda.controllers;

import com.caldeira.projetofinal.zelda.models.GameModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.caldeira.projetofinal.zelda.services.ZeldaGatewayService;

import java.util.List;

@RestController
@RequestMapping("/zelda")
public class ZeldaController{

    private final ZeldaGatewayService zeldaGatewayService;

    public ZeldaController(ZeldaGatewayService zeldaGatewayService) {
        this.zeldaGatewayService = zeldaGatewayService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GameModel>> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        List<GameModel> games = zeldaGatewayService.getAll(page, size);
        return ResponseEntity.ok(games);
    }

}
