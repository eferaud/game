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
public class GameCamera {

    //Seuil relatif à la boite de la caméra (boundary) au délà duquel le scroll se déclenche
    public final static double SCROLL_TRIGGER_THRESHOLD = 1d / 4d;

    //Position haut gauche
    private double offsetX;
    private double offsetY;

    private int width;
    private int height;

    public GameCamera(double offsetX, double offsetY, int width, int height) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void translateX(int translation) {
        this.offsetX = offsetX + translation;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.offsetX, this.offsetY, this.width, this.height);
    }

    public void translateY(int translation) {
        this.offsetY = offsetY + translation;
    }
}
