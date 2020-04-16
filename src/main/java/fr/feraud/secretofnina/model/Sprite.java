/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import java.util.List;
import javafx.geometry.Point2D;

/**
 * Personnage du jeu
 *
 * @author eric
 */
public abstract class Sprite extends Tile {

    private final static double VELOCITY = 0.10d;

    private double velocityX;
    private double velocityY;
    boolean currentlyMoving = false;

    //Nbr de point de vie au max
    private int lifeIndex;

    //Nbr de point de dégat pris
    private int hitsIndex;

    private DirectionEnum direction = DirectionEnum.DOWN;
    private MovementTypeEnum movementType = MovementTypeEnum.STOPED;
    //MAJ uniquement dans la méthode render
    private int loopCounter = 0;
    private boolean inCollision = false;
    private List<Point2D> clipping;

    public Sprite(int width, int height, int lifeIndex) {
        super(0, 0, width, height);
        this.velocityX = 0;
        this.velocityY = 0;
        this.hitsIndex = 0;
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
        this.mapPositionX += this.velocityX * time;
        this.mapPositionY += this.velocityY * time;
    }

    public void rollback(double time) {
        this.mapPositionX -= this.velocityX * time;
        this.mapPositionY -= this.velocityY * time;
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

    public boolean isInCollision() {
        return inCollision;
    }

    public void setInCollision(boolean inCollision) {
        this.inCollision = inCollision;
    }

    public void move(DirectionEnum direction, MovementTypeEnum movementType) {
        if (direction != null) {
            //System.out.println("move : " + movementType + " " + direction);
            this.direction = direction;
            this.movementType = movementType;

            //Cas demande direction
            this.velocityX = 0;
            this.velocityY = 0;
            if (!movementType.equals(MovementTypeEnum.STOPED)) {
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
                    case UP_RIGHT:
                        this.velocityY = -VELOCITY;
                        this.velocityX = VELOCITY;
                        break;
                    case UP_LEFT:
                        this.velocityY = -VELOCITY;
                        this.velocityX = -VELOCITY;
                        break;
                    case DOWN_LEFT:
                        this.velocityY = VELOCITY;
                        this.velocityX = -VELOCITY;
                        break;
                    case DOWN_RIGHT:
                        this.velocityY = VELOCITY;
                        this.velocityX = VELOCITY;
                        break;
                }
            }
        }
        if (movementType != null && movementType.equals(MovementTypeEnum.STOPED) && velocityY == 0 && velocityX == 0) {
            currentlyMoving = false;
        } else {
            currentlyMoving = true;
        }
        //System.out.println("loopCounter = " + loopCounter);
    }

    public void eraseVelocity() {
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public void setClipping(List<Point2D> clipping) {
        this.clipping = clipping;
    }

    public List<Point2D> getClipping() {
        return clipping;
    }

}
