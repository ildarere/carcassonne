package com.example.carcassonne.domain.game.settings;

import java.util.List;

public enum PlayerColor {
    RED,
    GREEN,
    YELLOW,
    BLUE;
    public static List<PlayerColor> DefaultColors() {
        return List.of(RED, GREEN, YELLOW, BLUE);
    }
}
