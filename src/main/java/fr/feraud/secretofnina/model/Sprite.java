/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import fr.feraud.secretofnina.DirectionEnum;
import javafx.geometry.Rectangle2D;

/**
 * Personnage du jeu
 *
 * @author eric
 */
public abstract class Sprite {

    private final static double VELOCITY = 0.05d;

    private int positionX;
    private int positionY;
    private double velocityX;
    private double velocityY;
    boolean currentlyMoving = false;
    private int width;
    private int height;
    //Nbr de point de vie au max
    private int lifeIndex;

    //Nbr de point de dégat pris
    private int hitsIndex;

    private DirectionEnum direction = DirectionEnum.DOWN;
    private MovementTypeEnum movementType = MovementTypeEnum.STOPED;
    //MAJ uniquement dans la méthode render
    private int loopCounter = 0;

    public Sprite(int width, int height, int lifeIndex) {
        this.width = width;
        this.height = height;
        this.positionX = 0;
        this.positionY = 0;
        this.velocityX = 0;
        this.velocityY = 0;
        this.hitsIndex = 0;
    }

    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    @Deprecated
    public void setVelocity(int x, int y) {
        this.velocityX = x;
        this.velocityY = y;
    }

    @Deprecated
    public void addVelocity(int x, int y) {
        this.velocityX += x;
        this.velocityY += y;
    }

    public void update(double time) {
        this.positionX += this.velocityX * time;
        this.positionY += this.velocityY * time;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public MovementTypeEnum getMovementType() {
        return movementType;
    }

    public int getLoopCounter() {
        return loopCounter;
    }

    public void setLoopCounter(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    @Deprecated
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.positionX, this.positionY, this.width, this.height);
    }

    @Deprecated
    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public int getLifeIndex() {
        return lifeIndex;
    }

    public void setLifeIndex(int lifeIndex) {
        this.lifeIndex = lifeIndex;
    }

    public int getHitsIndex() {
        return hitsIndex;
    }

    public void setHitsIndex(int hitsIndex) {
        this.hitsIndex = hitsIndex;
    }

    public boolean isCurrentlyMoving() {
        return currentlyMoving;
    }

    @Override
    public String toString() {
        return " Position: [" + this.positionX + "," + this.positionY + "]"
                + " Velocity: [" + this.velocityX + "," + this.velocityY + "]";
    }

    public void move(DirectionEnum direction, MovementTypeEnum movementType) {
        //System.out.println("move : " + movementType + " " + direction);
        this.direction = direction;
        this.movementType = movementType;

        //Cas relachement d'une direction
        if (movementType.equals(MovementTypeEnum.STOPED)) {
            switch (direction) {
                case RIGHT:
                    this.velocityX = 0;
                    break;
                case LEFT:
                    this.velocityX = 0;
                    break;
                case UP:
                    this.velocityY = 0;
                    break;
                case DOWN:
                    this.velocityY = 0;
                    break;
            }
            //Cas demande direction
        } else {
            switch (direction) {
                case RIGHT:
                    this.velocityX = VELOCITY;
                    break;
                case LEFT:
                    this.velocityX = -VELOCITY;
                    break;
                case UP:
                    this.velocityY = -VELOCITY;
                    break;
                case DOWN:
                    this.velocityY = VELOCITY;
                    break;
            }
        }

        if (movementType.equals(MovementTypeEnum.STOPED) && velocityY == 0 && velocityX == 0) {
            currentlyMoving = false;
        } else {
            currentlyMoving = true;
        }
        //System.out.println("loopCounter = " + loopCounter);
    }
}
