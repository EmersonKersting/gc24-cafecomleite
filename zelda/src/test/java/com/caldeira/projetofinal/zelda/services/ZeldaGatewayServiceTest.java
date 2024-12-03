package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.GameListResponseModel;
import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.models.GameResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

        String url = "https://zelda.fanapis.com/api/games?limit=5&page=0";
        GameListResponseModel mockResponse = new GameListResponseModel();
        mockResponse.setData(List.of(new GameModel("1", "Zelda: Ocarina of Time", "Action", "1998", "Nintendo", "N64")));
        when(restTemplate.getForObject(url, GameListResponseModel.class)).thenReturn(mockResponse);

        List<GameModel> games = zeldaGatewayService.getAll(0, 5);

        assertNotNull(games);
        assertEquals(1, games.size());
        assertEquals("Zelda: Ocarina of Time", games.get(0).getName());
    }

    @Test
    void testGetAllWithNullResponse() {
        String url = "https://zelda.fanapis.com/api/games?limit=5&page=0";
        when(restTemplate.getForObject(url, GameListResponseModel.class)).thenReturn(null);

        List<GameModel> games = zeldaGatewayService.getAll(0, 5);

        assertNotNull(games);
        assertTrue(games.isEmpty());
    }

    @Test
    void testGetAllWithNullParameters() {

        Integer page = null;
        Integer size = null;
        String expectedUrl = UriComponentsBuilder
                .fromHttpUrl("https://zelda.fanapis.com/api/games")
                .queryParam("limit", 5) // Default size
                .queryParam("page", 0)  // Default page
                .toUriString();

        GameListResponseModel mockResponse = new GameListResponseModel();
        mockResponse.setData(List.of(new GameModel("1", "Zelda: A Link to the Past", "Action", "1991", "Nintendo", "SNES")));
        when(restTemplate.getForObject(expectedUrl, GameListResponseModel.class)).thenReturn(mockResponse);

        List<GameModel> games = zeldaGatewayService.getAll(page, size);

        assertNotNull(games);
        assertEquals(1, games.size());
        assertEquals("Zelda: A Link to the Past", games.get(0).getName());
    }

    @Test
    void testGetById() {
        String id = "1";
        String url = "https://zelda.fanapis.com/api/games/" + id;
        GameResponseModel mockResponse = new GameResponseModel();
        mockResponse.setSuccess(true);
        mockResponse.setData(new GameModel(id, "Zelda: Breath of the Wild", "Adventure", "2017", "Nintendo", "Switch"));
        when(restTemplate.getForObject(url, GameResponseModel.class)).thenReturn(mockResponse);

        GameModel game = zeldaGatewayService.getById(id);

        assertNotNull(game);
        assertEquals("Zelda: Breath of the Wild", game.getName());
    }

    @Test
    void testGetByIdWithNullResponse() {

        String id = "1";
        String expectedUrl = "https://zelda.fanapis.com/api/games/" + id;

        when(restTemplate.getForObject(expectedUrl, GameResponseModel.class)).thenReturn(null);

        GameModel result = zeldaGatewayService.getById(id);

        assertNull(result);
    }

    @Test
    void testGetByIdWithUnsuccessfulResponse() {

        String id = "1";
        String expectedUrl = "https://zelda.fanapis.com/api/games/" + id;

        GameResponseModel mockResponse = new GameResponseModel();
        mockResponse.setSuccess(false);
        when(restTemplate.getForObject(expectedUrl, GameResponseModel.class)).thenReturn(mockResponse);

        GameModel result = zeldaGatewayService.getById(id);

        assertNull(result);
    }

    @Test
    void testGetAllByName() {

        String name = "Zelda";
        String expectedUrl = UriComponentsBuilder.fromHttpUrl("https://zelda.fanapis.com/api/games")
                .queryParam("name", name)
                .toUriString();

        GameListResponseModel mockResponse = new GameListResponseModel();
        mockResponse.setSuccess(true);
        mockResponse.setData(List.of(new GameModel("1", "Zelda: Ocarina of Time",
                "Action", "1998", "Nintendo", "N64")));

        when(restTemplate.getForObject(expectedUrl, GameListResponseModel.class)).thenReturn(mockResponse);

        List<GameModel> games = zeldaGatewayService.getAllByName(name);

        assertNotNull(games);
        assertEquals(1, games.size());
        assertEquals("Zelda: Ocarina of Time", games.get(0).getName());
    }

    @Test
    void testGetAllByNameWithNullResponse() {

        String name = "Zelda";
        String expectedUrl = UriComponentsBuilder.fromHttpUrl("https://zelda.fanapis.com/api/games")
                .queryParam("name", name)
                .toUriString();

        when(restTemplate.getForObject(expectedUrl, GameListResponseModel.class)).thenReturn(null);

        List<GameModel> games = zeldaGatewayService.getAllByName(name);

        assertNotNull(games);
        assertTrue(games.isEmpty());
    }

    @Test
    void testGetAllByNameWithUnsuccessfulResponse() {

        String name = "Zelda";
        String expectedUrl = UriComponentsBuilder.fromHttpUrl("https://zelda.fanapis.com/api/games")
                .queryParam("name", name)
                .toUriString();

        GameListResponseModel mockResponse = new GameListResponseModel();
        mockResponse.setSuccess(false);
        when(restTemplate.getForObject(expectedUrl, GameListResponseModel.class)).thenReturn(mockResponse);

        List<GameModel> games = zeldaGatewayService.getAllByName(name);

        assertNotNull(games);
        assertTrue(games.isEmpty());
    }
}


