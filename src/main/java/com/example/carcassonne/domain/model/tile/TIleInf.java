package com.example.carcassonne.domain.model.tile;

import java.io.Serializable;

public class TIleInf implements Serializable {
    private int X;
    private int Y;
    private int maxRotation;
    private int rotation;
    private TileType type;
    private int numOgLRotation;
    private int numOgRRotation;

    public TIleInf(int X, int Y, int maxRotation, int rotation, TileType type, int numOgLRotation, int numOgRRotation) {
        this.X = X;
        this.Y = Y;
        this.maxRotation = maxRotation;
        this.rotation = rotation;
        this.type = type;
        this.numOgLRotation = numOgLRotation;
        this.numOgRRotation = numOgRRotation;
    }

    public int getNumOgLRotation() {
        return numOgLRotation;
    }

    public void setNumOgLRotation(int numOgLRotation) {
        this.numOgLRotation = numOgLRotation;
    }

    public int getNumOgRRotation() {
        return numOgRRotation;
    }

    public void setNumOgRRotation(int numOgRRotation) {
        this.numOgRRotation = numOgRRotation;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getMaxRotation() {
        return maxRotation;
    }

    public void setMaxRotation(int maxRotation) {
        this.maxRotation = maxRotation;
    }

    public TileRotation getRotation() {

        return TileRotation.getValue(rotation);
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TIleInf{" +
                "X=" + X +
                ", Y=" + Y +
                ", maxRotation=" + maxRotation +
                ", rotation=" + rotation +
                ", type=" + type +
                '}';
    }
}
