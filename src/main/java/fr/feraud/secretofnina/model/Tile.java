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

    protected double mapPositionX;
    protected double mapPositionY;
    private final int width;
    private final int height;

    public Tile(int positionX, int positionY, int width, int height) {
        this.mapPositionX = positionX;
        this.mapPositionY = positionY;
        this.width = width;
        this.height = height;
    }

    protected void setPosition(int x, int y) {
        this.mapPositionX = x;
        this.mapPositionY = y;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.mapPositionX, this.mapPositionY, this.width, this.height);
    }

    public double getMapPositionX() {
        return mapPositionX;
    }

    public double getMapPositionY() {
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
        int hash = 3;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.mapPositionX) ^ (Double.doubleToLongBits(this.mapPositionX) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.mapPositionY) ^ (Double.doubleToLongBits(this.mapPositionY) >>> 32));
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
