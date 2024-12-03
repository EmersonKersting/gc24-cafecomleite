package com.caldeira.projetofinal.zelda.models;

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

}
