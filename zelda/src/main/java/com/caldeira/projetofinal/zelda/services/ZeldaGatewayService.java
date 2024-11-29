package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.GameListResponseModel;
import com.caldeira.projetofinal.zelda.models.GameModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

}
