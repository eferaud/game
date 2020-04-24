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
public abstract class Sprite extends Tile implements ICollisible {

    private final static double VELOCITY = 0.10d;

    private double velocityX;
    private double velocityY;

    //Nbr de point de vie au max
    private int lifeIndex;

    //Nbr de point de dégat pris
    private int hitsIndex;

    private final SpriteEvent spriteEvent = new SpriteEvent(DirectionEnum.DOWN, MovementTypeEnum.STOPED);
    private final SpriteEvent prevSpriteEvent = new SpriteEvent(DirectionEnum.DOWN, MovementTypeEnum.STOPED);
    //le sprite est en train de faire uen action qui déroule des animation
    //on bloque toute commande
    private SpriteStatusEnum status = SpriteStatusEnum.STAND;
    private SpriteStatusEnum prevStatus = SpriteStatusEnum.STAND;

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

    /**
     * Uniquement utilisable par le GameCollision engine
     *
     * @param time
     */
    @Override
    public void update(double time) {
        this.mapPositionX += this.velocityX * time;
        this.mapPositionY += this.velocityY * time;
    }

    /**
     * Uniquement utilisable par le GameCollision engine
     *
     * @param time
     */
    @Override
    public void rollback(double time) {
        this.mapPositionX -= this.velocityX * time;
        this.mapPositionY -= this.velocityY * time;
    }

    /**
     * Uniquement utilisable par le moteur de collision
     *
     * @param inCollision
     */
    @Override
    public void setInCollision(boolean inCollision) {
        this.inCollision = inCollision;
    }

    /**
     * Uniquement utilisable par le moteur de collision
     */
    @Override
    public void eraseVelocity() {
        this.velocityX = 0;
        this.velocityY = 0;
    }

    /**
     * Uniquement utilisable par le moteur de collision
     *
     * @return
     */
    @Override
    public List<Point2D> getClipping() {
        return clipping;
    }

    /**
     * Uniquement pour la couche render
     *
     * @return
     */
    public SpriteStatusEnum getStatus() {
        return status;
    }

    /**
     * Uniquement pour la couche render
     *
     * @return
     */
    public int getLoopCounter() {
        return loopCounter;
    }

    /**
     * Uniquement pour la couche render
     */
    public void incrementLoop() {
        loopCounter = loopCounter + 1;
    }

    /**
     * Uniquement pour la couche render
     */
    public void eraseCounter() {
        loopCounter = 0;
    }

    /**
     * Uniquement pour la couche render
     *
     * @param clipping
     */
    public void setClipping(List<Point2D> clipping) {
        this.clipping = clipping;
    }

    /**
     * Uniquement pour la couche render
     *
     * @return
     */
    public SpriteEvent getSpriteEvent() {
        return spriteEvent;
    }

    /**
     * Uniquement pour la couche render
     */
    public void notifyEndAnimation() {

        status = prevStatus;
        spriteEvent.setMovementType(prevSpriteEvent.getMovementType()); //@FIXME que si pas reçu le message stop
        eraseCounter();
        System.out.println("notifyEndAnimation status->" + status + " MovementType->" + spriteEvent.getMovementType());

        move(spriteEvent.getDirection(), spriteEvent.getMovementType());
    }

    /**
     * Uniquement par le controlleur du joueur ou l'IA
     *
     * @param direction Si null, on garde l'ancienne direction
     * @param movementType Si null on garde l'ancien mouvement
     */
    public void move(DirectionEnum direction, MovementTypeEnum movementType) {
        System.out.println(direction + " " + movementType);

        eraseVelocity();

        //Cas d'une animation et relachement du pavé directionnel
        if (status.isAnimated() && MovementTypeEnum.STOPED.equals(movementType)) {
            prevSpriteEvent.setMovementType(MovementTypeEnum.STOPED);
            prevStatus = SpriteStatusEnum.STAND;
        }

        //On bloque toute commande si le sprite est dans une animation
        if (!status.isAnimated()) {
            prevSpriteEvent.setDirection(spriteEvent.getDirection());
            prevSpriteEvent.setMovementType(spriteEvent.getMovementType());
            if (direction != null) {
                spriteEvent.setDirection(direction);
            }
            if (movementType != null) {
                spriteEvent.setMovementType(movementType);
            }

            //Cas d'un arret
            if (MovementTypeEnum.STOPED.equals(movementType)) {
                eraseCounter();
            }

            //Cas demande direction
            if (MovementTypeEnum.WALK.equals(movementType) || MovementTypeEnum.RUN.equals(movementType)) {
                processWalk(direction);
            }

            if (MovementTypeEnum.ATTACK.equals(movementType)) {
                processAttack();
            }
        }
    }

    private void processWalk(DirectionEnum direction) {
        prevStatus = status;
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
        prevStatus = status;
        eraseCounter();
        status = SpriteStatusEnum.ATTACK;
    }

}
