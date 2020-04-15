/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Personnage du jeu
 *
 * @author eric
 */
public abstract class Sprite {

    private final static double VELOCITY = 0.08d;

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
    private boolean inCollision = false;
    private List<Point2D> clipping;

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

    public void rollback(double time) {
        this.positionX -= this.velocityX * time;
        this.positionY -= this.velocityY * time;
    }

    public int getPositionX() {
        return positionX;
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

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.positionX, this.positionY, this.width, this.height);
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

    @Override
    public String toString() {
        return " Position: [" + this.positionX + "," + this.positionY + "]"
                + " Velocity: [" + this.velocityX + "," + this.velocityY + "]";
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.positionX;
        hash = 13 * hash + this.positionY;
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
        final Sprite other = (Sprite) obj;
        if (this.positionX != other.positionX) {
            return false;
        }
        return true;
    }

    public void setClipping(List<Point2D> clipping) {
        this.clipping = clipping;
    }

    public List<Point2D> getClipping() {
        return clipping;
    }

}
