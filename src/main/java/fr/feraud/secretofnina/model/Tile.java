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

    protected int positionX;
    protected int positionY;
    private final int width;
    private final int height;

    public Tile(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.positionX, this.positionY, this.width, this.height);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.positionX;
        hash = 13 * hash + this.positionY;
        return hash;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
        final Sprite other = (Sprite) obj;
        if (this.positionX != other.positionX) {
            return false;
        }
        return true;
    }

}
