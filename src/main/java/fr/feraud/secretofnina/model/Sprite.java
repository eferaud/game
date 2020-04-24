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

    //Nbr de point de vie au max
    private int lifeIndex;

    //Nbr de point de dégat pris
    private int hitsIndex;

    private final SpriteEvent spriteEvent = new SpriteEvent(DirectionEnum.DOWN, MovementTypeEnum.STOPED);
    //le sprite est en train de faire uen action qui déroule des animation
    //on bloque toute commande
    private SpriteStatusEnum status = SpriteStatusEnum.STAND;

    //MAJ uniquement dans la méthode render
    private int loopCounter = 0;
    private boolean inCollision = false;
    private List<Point2D> clipping;

    public Sprite(int width, int height, int lifeIndex) {
        //Pas de position pour le moment car on a pas de charset @TODO
        super(0, 0, width, height, true, true);
        this.velocityX = 0;
        this.velocityY = 0;
        this.hitsIndex = 0;
    }

    public void update(double time) {
        this.mapPositionX += this.velocityX * time;
        this.mapPositionY += this.velocityY * time;
    }

    public void rollback(double time) {
        this.mapPositionX -= this.velocityX * time;
        this.mapPositionY -= this.velocityY * time;
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

    public boolean isInCollision() {
        return inCollision;
    }

    public void setInCollision(boolean inCollision) {
        this.inCollision = inCollision;
    }

    /**
     *
     * @param direction Ne doit pas être null
     * @param movementType Ne doit pas être null
     */
    public void move(DirectionEnum direction, MovementTypeEnum movementType) {
        System.out.println(direction + " " + movementType);

        eraseVelocity();

        //On bloque toute commande si le sprite est dans une animation
        if (!status.isAnimated()) {
            spriteEvent.setDirection(direction);
            spriteEvent.setMovementType(movementType);

            //Cas demande direction
            if (MovementTypeEnum.WALK.equals(movementType) || MovementTypeEnum.RUN.equals(movementType)) {
                processWalk(direction);
            }

            if (MovementTypeEnum.ATTACK.equals(movementType)) {
                processAttack();
            }
        }
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

    public SpriteEvent getSpriteEvent() {
        return spriteEvent;
    }

    public SpriteStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SpriteStatusEnum status) {
        this.status = status;
    }

    private void processWalk(DirectionEnum direction) {
        status = SpriteStatusEnum.WALKING; //@TODO prévoir le cas RUN
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

    private void processAttack() {
        status = SpriteStatusEnum.ATTACK;
    }

    public void notifyEndAnimation() {
        status = SpriteStatusEnum.STAND;
        spriteEvent.setMovementType(MovementTypeEnum.STOPED);
    }
}
