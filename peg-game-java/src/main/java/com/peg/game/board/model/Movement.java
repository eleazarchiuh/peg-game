package com.peg.game.board.model;

public class Movement {
    private int originX;
    private int originY;
    private int intermediateX;
    private int intermediateY;
    private int destinationX;
    private int destinationY;

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public int getIntermediateX() {
        return intermediateX;
    }

    public void setIntermediateX(int intermediateX) {
        this.intermediateX = intermediateX;
    }

    public int getIntermediateY() {
        return intermediateY;
    }

    public void setIntermediateY(int intermediateY) {
        this.intermediateY = intermediateY;
    }

    public int getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(int destinationX) {
        this.destinationX = destinationX;
    }

    public int getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(int destinationY) {
        this.destinationY = destinationY;
    }
}
