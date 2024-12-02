package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.GameListResponseModel;
import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.models.GameResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ZeldaGatewayServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private ZeldaGatewayService zeldaGatewayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        zeldaGatewayService = new ZeldaGatewayService(restTemplate);
    }

    @Test
    void testGetAll() {
        // Arrange
        String url = "https://zelda.fanapis.com/api/games?limit=5&page=0";
        GameListResponseModel mockResponse = new GameListResponseModel();
        mockResponse.setData(List.of(new GameModel("1", "Zelda: Ocarina of Time", "Action", "1998", "Nintendo", "N64")));
        when(restTemplate.getForObject(url, GameListResponseModel.class)).thenReturn(mockResponse);

        // Act
        List<GameModel> games = zeldaGatewayService.getAll(0, 5);

        // Assert
        assertNotNull(games);
        assertEquals(1, games.size());
        assertEquals("Zelda: Ocarina of Time", games.get(0).getName());
    }

    @Test
    void testGetAllWithNullResponse() {
        // Arrange
        String url = "https://zelda.fanapis.com/api/games?limit=5&page=0";
        when(restTemplate.getForObject(url, GameListResponseModel.class)).thenReturn(null);

        // Act
        List<GameModel> games = zeldaGatewayService.getAll(0, 5);

        // Assert
        assertNotNull(games);
        assertTrue(games.isEmpty());
    }

    @Test
    void testGetById() {
        // Arrange
        String id = "1";
        String url = "https://zelda.fanapis.com/api/games/" + id;
        GameResponseModel mockResponse = new GameResponseModel();
        mockResponse.setSuccess(true);
        mockResponse.setData(new GameModel(id, "Zelda: Breath of the Wild", "Adventure", "2017", "Nintendo", "Switch"));
        when(restTemplate.getForObject(url, GameResponseModel.class)).thenReturn(mockResponse);

        // Act
        GameModel game = zeldaGatewayService.getById(id);

        // Assert
        assertNotNull(game);
        assertEquals("Zelda: Breath of the Wild", game.getName());
    }

    @Test
    void testGetByIdNotFound() {
        // Arrange
        String id = "999";
        String url = "https://zelda.fanapis.com/api/games/" + id;
        when(restTemplate.getForObject(url, GameResponseModel.class)).thenReturn(null);

        // Act
        GameModel game = zeldaGatewayService.getById(id);

        // Assert
        assertNull(game);
    }

    @Test
    void testGetAllByName() {
        // Arrange
        String name = "Zelda";
        String url = "https://api.zelda.com/games?name=" + name;
        GameListResponseModel mockResponse = new GameListResponseModel();
        mockResponse.setData(List.of(new GameModel("1", "Zelda: Ocarina of Time", "Action", "1998", "Nintendo", "N64")));
        when(restTemplate.getForObject(url, GameListResponseModel.class)).thenReturn(mockResponse);

        // Act
        List<GameModel> games = zeldaGatewayService.getAllByName(name);

        // Assert
        assertNotNull(games);
        assertEquals(1, games.size());
        assertEquals("Zelda: Ocarina of Time", games.get(0).getName());
    }

    @Test
    void testGetAllByNameEmptyList() {
        // Arrange
        String name = "NonExistentGame";
        String url = "https://api.zelda.com/games?name=" + name;
        when(restTemplate.getForObject(url, GameListResponseModel.class)).thenReturn(new GameListResponseModel());

        // Act
        List<GameModel> games = zeldaGatewayService.getAllByName(name);

        // Assert
        assertNotNull(games);
        assertTrue(games.isEmpty());
    }
}
