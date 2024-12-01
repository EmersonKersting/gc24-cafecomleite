package com.caldeira.projetofinal.zelda.controllers;

import com.caldeira.projetofinal.zelda.models.GameModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.caldeira.projetofinal.zelda.services.ZeldaGatewayService;

import java.util.Collections;
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

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<GameModel> getById(@PathVariable String id) {
        GameModel game = zeldaGatewayService.getById(id);

        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(game);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<List<GameModel>> getAllByName(@PathVariable String name) {
        List<GameModel> games = zeldaGatewayService.getAllByName(name);
        return ResponseEntity.ok(games);
    }


}
