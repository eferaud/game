/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import javafx.geometry.Rectangle2D;

/**
 *
 * @author eric
 */
public abstract class Tile {

    protected int mapPositionX;
    protected int mapPositionY;
    private final int width;
    private final int height;

    public Tile(int positionX, int positionY, int width, int height) {
        this.mapPositionX = positionX;
        this.mapPositionY = positionY;
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.mapPositionX = x;
        this.mapPositionY = y;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.mapPositionX, this.mapPositionY, this.width, this.height);
    }

    public int getMapPositionX() {
        return mapPositionX;
    }

    public int getMapPositionY() {
        return mapPositionY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.mapPositionX;
        hash = 67 * hash + this.mapPositionY;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tile other = (Tile) obj;
        if (this.mapPositionX != other.mapPositionX) {
            return false;
        }
        if (this.mapPositionY != other.mapPositionY) {
            return false;
        }
        return true;
    }

}
