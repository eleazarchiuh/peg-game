package com.peg.game.board.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int levels;
    private List<List<Node>> nodes;
    private Long playerId;

    public Board(int levels) {
        this.levels = levels;
        this.nodes = new ArrayList<>();
    }
    public Node getNode(int level, int index) {
        return nodes.get(level).get(index);
    }

    public void addLevel(List<Node> level) {
        if (nodes.size() >= levels) {
            throw new IllegalArgumentException("Cannot add more levels than the specified limit.");
        }
        if (level.size() != nodes.size() + 1) {
            throw new IllegalArgumentException("The number of nodes in the level must match the level number.");
        }
        nodes.add(level);
    }

    public List<List<Node>> getNodes() {
        return nodes;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
