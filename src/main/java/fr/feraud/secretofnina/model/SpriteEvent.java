/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import java.util.Objects;

/**
 *
 * @author eric
 */
public class SpriteEvent {

    private DirectionEnum direction;
    private MovementTypeEnum movementType;

    public SpriteEvent(DirectionEnum direction, MovementTypeEnum movementType) {
        this.direction = direction;
        this.movementType = movementType;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public MovementTypeEnum getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementTypeEnum movementType) {
        this.movementType = movementType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.direction);
        hash = 59 * hash + Objects.hashCode(this.movementType);
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
        final SpriteEvent other = (SpriteEvent) obj;
        if (this.direction != other.direction) {
            return false;
        }
        if (this.movementType != other.movementType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SpriteEvent{" + "direction=" + direction + ", movementType=" + movementType + '}';
    }

}
