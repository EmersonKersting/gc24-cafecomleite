package com.caldeira.projetofinal.zelda.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameListResponseModel {

    private boolean success;
    private int count;
    private List<GameModel> data;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GameModel {

        private String name;
        private String description;
        private String developer;
        private String publisher;

        @JsonProperty("released_date")
        private String releasedDate;
        private String id;
    }
}
