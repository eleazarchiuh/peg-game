package com.peg.game.board.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Integer value;
    private int level;
    private int index;
    List<Node> adjacent = new ArrayList<>();

    public Node(Integer value, int level, int index) {
        this.value = value;
        this.level = level;
        this.index = index;
    }

    public void link(Node node) {
        adjacent.add(node);
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", level=" + level +
                ", index=" + index +
                '}';
    }
}
