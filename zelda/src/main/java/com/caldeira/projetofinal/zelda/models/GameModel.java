package com.caldeira.projetofinal.zelda.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameModel {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("developer")
    private String developer;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("released_date")
    private String releasedDate;

    @JsonProperty("id")
    private String id;
}
