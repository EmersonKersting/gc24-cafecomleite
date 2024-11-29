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

    private String id;
    private String name;
    private String description;
    private String developer;
    private String publisher;
    @JsonProperty("released_date")
    private String releasedDate;
}
