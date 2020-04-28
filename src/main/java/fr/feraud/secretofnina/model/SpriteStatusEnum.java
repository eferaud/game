/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

/**
 *
 * @author eric
 */
public enum SpriteStatusEnum {

    ATTACK(true),
    WALKING(false),
    STAND(false),
    HURT(true),
    DIYING(true),
    DEAD(false);

    boolean animated;

    private SpriteStatusEnum(boolean animated) {
        this.animated = animated;
    }

    public boolean isAnimated() {
        return animated;
    }
}
