/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.gamepad;

import fr.feraud.secretofnina.model.DirectionEnum;
import fr.feraud.secretofnina.model.MovementTypeEnum;
import fr.feraud.secretofnina.model.Sprite;
import java.util.logging.Logger;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

/**
 * //Controller (XBOX 360 For Windows)
 *
 * @author eric
 */
public class GamePadController {

    private final static Logger LOG = Logger.getLogger(GamePadController.class.getName());

    private Controller controller;
    private final Sprite player;

    //B : Bouton 0
    //A : Bouton 1
    //Y : Bouton 2
    //X : Bouton 3
    //select : Bouton 6
    //start : Bouton 7
    public GamePadController(Sprite player) {
        this.player = player;
    }

    public synchronized void run() {

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (final net.java.games.input.Controller c : controllers) {
            if (c.poll() && Controller.Type.GAMEPAD.equals(c.getType())) {
                controller = c;
            }
        }

        EventQueue queue = controller.getEventQueue();

        Event event = new Event();
        while (queue.getNextEvent(event)) {
            Component comp = event.getComponent();
            float value = event.getValue();
            if (comp.getName().equals("Bouton 0")) {
                if (value == 1.0f) {
                    player.move(player.getSpriteEvent().getDirection(), MovementTypeEnum.ATTACK);
                } else {
                    // buffer.append("Off");
                }
            } else if (comp.getName().equals("Commande de pouce")) {
                if (value == 0.0f) {
                    player.move(player.getSpriteEvent().getDirection(), MovementTypeEnum.STOPED);
                } else if (value == 0.5f) {
                    player.move(DirectionEnum.RIGHT, MovementTypeEnum.WALK);
                } else if (value == 1.0f) {
                    player.move(DirectionEnum.LEFT, MovementTypeEnum.WALK);
                } else if (value == 0.25f) {
                    player.move(DirectionEnum.UP, MovementTypeEnum.WALK);
                } else if (value == 0.75f) {
                    player.move(DirectionEnum.DOWN, MovementTypeEnum.WALK);
                } else if (value == 0.125f) {
                    player.move(DirectionEnum.UP_LEFT, MovementTypeEnum.WALK);
                } else if (value == 0.375f) {
                    player.move(DirectionEnum.UP_RIGHT, MovementTypeEnum.WALK);
                } else if (value == 0.875f) {
                    player.move(DirectionEnum.DOWN_LEFT, MovementTypeEnum.WALK);
                } else if (value == 0.625f) {
                    player.move(DirectionEnum.DOWN_RIGHT, MovementTypeEnum.WALK);
                }
            }
        }
    }

}
