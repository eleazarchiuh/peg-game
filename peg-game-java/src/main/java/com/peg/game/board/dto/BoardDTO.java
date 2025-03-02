package com.peg.game.board.dto;

import java.util.List;

public class BoardDTO {
    private List<List<Integer>> levels;
    private Long playerId;

    public List<List<Integer>> getLevels() {
        return levels;
    }

    public void setLevels(List<List<Integer>> levels) {
        this.levels = levels;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
