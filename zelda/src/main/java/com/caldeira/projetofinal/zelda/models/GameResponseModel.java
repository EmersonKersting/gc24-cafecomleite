package com.caldeira.projetofinal.zelda.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameResponseModel {

    private boolean success;
    private GameModel data;

}
