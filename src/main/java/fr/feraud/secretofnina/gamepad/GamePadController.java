/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.gamepad;

import fr.feraud.secretofnina.model.DirectionEnum;
import fr.feraud.secretofnina.model.MovementTypeEnum;
import fr.feraud.secretofnina.model.Sprite;
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

    private GamePadEventHandler eventHandler;
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

    public void run() {

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (final net.java.games.input.Controller c : controllers) {
            if (c.poll() && Controller.Type.GAMEPAD.equals(c.getType())) {
                controller = c;
            }
        }

        EventQueue queue = controller.getEventQueue();
        controller.setEventQueueSize(10);
        Event event = new Event();

        while (queue.getNextEvent(event)) {
            Component comp = event.getComponent();
            float value = event.getValue();
            if (!comp.getName().equals("Commande de pouce")) {
                if (value == 1.0f) {
                    //buffer.append("On");
                } else {
                    // buffer.append("Off");
                }
            } else {
                if (value == 0.0f) {
                    player.move(player.getDirection(), MovementTypeEnum.STOPED);
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

        /*while (queue.getNextEvent(event)) {
            StringBuffer buffer = new StringBuffer(controller.getName());
            buffer.append(" at ");
            buffer.append(event.getNanos()).append(", ");
            Component comp = event.getComponent();
            buffer.append(comp.getName()).append(" changed to ");
            float value = event.getValue();
            if (comp.isAnalog()) {
                buffer.append(value);
            } else {
                if (value == 1.0f) {
                    buffer.append("On");
                } else {
                    buffer.append("Off");
                }
            }
            buffer.append(value);
            System.out.println(buffer.toString());
        }*/
    }

}
/*
HAUT
Controller (XBOX 360 For Windows) at 15941546000000, Axe Y changed to -4.5776367E-5
Controller (XBOX 360 For Windows) at 15941546000000, Rotation Y changed to -4.5776367E-5

Controller (XBOX 360 For Windows) at 15941546000000, Commande de pouce changed to Off
Controller (XBOX 360 For Windows) at 15941687000000, Commande de pouce changed to Off


BAS
Controller (XBOX 360 For Windows) at 15970531000000, Commande de pouce changed to Off
Controller (XBOX 360 For Windows) at 15970703000000, Commande de pouce changed to Off


bouton
Controller (XBOX 360 For Windows) at 16023265000000, Bouton 0 changed to On
Controller (XBOX 360 For Windows) at 16023390000000, Bouton 0 changed to Off
Controller (XBOX 360 For Windows) at 16025406000000, Bouton 1 changed to On
Controller (XBOX 360 For Windows) at 16025546000000, Bouton 1 changed to Off
Controller (XBOX 360 For Windows) at 16027015000000, Bouton 2 changed to On
Controller (XBOX 360 For Windows) at 16027140000000, Bouton 2 changed to Off
Controller (XBOX 360 For Windows) at 16027562000000, Bouton 3 changed to On
Controller (XBOX 360 For Windows) at 16027734000000, Bouton 3 changed to Off
 */
