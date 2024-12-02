package com.caldeira.projetofinal.zelda.controllers;

import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.services.ZeldaGatewayService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZeldaControllerTest {

    @Mock
    private ZeldaGatewayService zeldaGatewayService;

    @InjectMocks
    private ZeldaController zeldaController;

    ZeldaControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Arrange
        List<GameModel> mockGames = List.of(
                new GameModel("1", "Zelda Game", "Adventure game", "RPG", "1986-02-21", "Nintendo")
        );
        when(zeldaGatewayService.getAll(null, null)).thenReturn(mockGames);

        // Act
        ResponseEntity<List<GameModel>> response = zeldaController.getAll(null, null);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(zeldaGatewayService, times(1)).getAll(null, null);
    }

    @Test
    void testGetByIdFound() {
        // Arrange
        GameModel mockGame = new GameModel("1", "Zelda Game", "Adventure game", "RPG", "1986-02-21", "Nintendo");
        when(zeldaGatewayService.getById("1")).thenReturn(mockGame);

        // Act
        ResponseEntity<GameModel> response = zeldaController.getById("1");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getId());
        verify(zeldaGatewayService, times(1)).getById("1");
    }

    @Test
    void testGetByIdNotFound() {
        // Arrange
        when(zeldaGatewayService.getById("99")).thenReturn(null);

        // Act
        ResponseEntity<GameModel> response = zeldaController.getById("99");

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(zeldaGatewayService, times(1)).getById("99");
    }

    @Test
    void testGetAllByName() {
        // Arrange
        List<GameModel> mockGames = List.of(
                new GameModel("1", "Zelda Game", "Adventure game", "RPG", "1986-02-21", "Nintendo")
        );
        when(zeldaGatewayService.getAllByName("Zelda")).thenReturn(mockGames);

        // Act
        ResponseEntity<List<GameModel>> response = zeldaController.getAllByName("Zelda");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(zeldaGatewayService, times(1)).getAllByName("Zelda");
    }
}
