package com.caldeira.projetofinal.zelda.controllers;

import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.services.ZeldaGatewayService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZeldaControllerTest {

    // Criação de uma instância simples de RestTemplate
    private final RestTemplate restTemplate = new RestTemplate();

    // Implementação simples (stub) de ZeldaGatewayService, agora passando o RestTemplate
    private final ZeldaGatewayService zeldaGatewayService = new ZeldaGatewayService(restTemplate) {
        @Override
        public List<GameModel> getAll(Integer page, Integer size) {
            return List.of(new GameModel("1", "Zelda Game", "Adventure game", "RPG", "1986-02-21", "Nintendo"));
        }

        @Override
        public GameModel getById(String id) {
            if ("1".equals(id)) {
                return new GameModel("1", "Zelda Game", "Adventure game", "RPG", "1986-02-21", "Nintendo");
            }
            return null;
        }

        @Override
        public List<GameModel> getAllByName(String name) {
            return List.of(new GameModel("1", "Zelda Game", "Adventure game", "RPG", "1986-02-21", "Nintendo"));
        }
    };

    // Criação do controlador, injetando a implementação "stub" do ZeldaGatewayService
    private final ZeldaController zeldaController = new ZeldaController(zeldaGatewayService);

    @Test
    void testGetAll() {
        // Act
        ResponseEntity<List<GameModel>> response = zeldaController.getAll(null, null);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Zelda Game", response.getBody().get(0).getName());
    }

    @Test
    void testGetByIdFound() {
        // Act
        ResponseEntity<GameModel> response = zeldaController.getById("1");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getId());
        assertEquals("Zelda Game", response.getBody().getName());
    }

    @Test
    void testGetByIdNotFound() {
        // Act
        ResponseEntity<GameModel> response = zeldaController.getById("99");

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllByName() {
        // Act
        ResponseEntity<List<GameModel>> response = zeldaController.getAllByName("Zelda");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Zelda Game", response.getBody().get(0).getName());
    }
}
