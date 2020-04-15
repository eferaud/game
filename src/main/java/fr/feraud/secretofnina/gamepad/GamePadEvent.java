/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.gamepad;

import javafx.event.EventTarget;
import javafx.scene.input.InputEvent;

/**
 *
 * @author eric
 */
public class GamePadEvent extends InputEvent {

    private static final long serialVersionUID = 2231266847422883646L;

    private transient double x;
    private transient double y;

    public GamePadEvent(Object source, EventTarget target, double x, double y) {
        super(source, target, null /* enventType */);
        this.x = x;
        this.y = y;
    }

    public final double getX() {
        return x;
    }

    public final double getY() {
        return y;
    }
}
