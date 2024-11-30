package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.GameListResponseModel;
import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.models.GameResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class ZeldaGatewayService {

    private final RestTemplate restTemplate;

    public ZeldaGatewayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GameModel> getAll(Integer page, Integer size) {
        int defaultPage = (page != null) ? page : 0;
        int defaultSize = (size != null) ? size : 5;

        String url = UriComponentsBuilder
                .fromHttpUrl("https://zelda.fanapis.com/api/games")
                .queryParam("limit", defaultSize)
                .queryParam("page", defaultPage)
                .toUriString();

        GameListResponseModel response = restTemplate.getForObject(url, GameListResponseModel.class);

        return response != null ? response.getData() : List.of();
    }

    public GameModel getById(String id) {
        String url = "https://zelda.fanapis.com/api/games/" + id;

        GameResponseModel response = restTemplate.getForObject(url, GameResponseModel.class);

        if (response == null || !response.isSuccess()) {
            return null;
        }
        return response.getData();
    }

    public List<GameModel> getAllByName(String name) {
        String url = "https://api.zelda.com/games?name=" + name;

        GameListResponseModel response = restTemplate.getForObject(url, GameListResponseModel.class);

        if (response == null || response.getData() == null) {
            return Collections.emptyList();
        }
        return response.getData();
    }

}
