/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.control;

import fr.feraud.secretofnina.DirectionEnum;
import fr.feraud.secretofnina.model.MovementTypeEnum;
import fr.feraud.secretofnina.model.Sprite;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author eric
 */
public class PlayerEventHandler implements EventHandler<KeyEvent> {

    private final Sprite player;

    public PlayerEventHandler(Sprite player) {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        //System.out.println("INPUT : " + keyEvent.getEventType().getName() + " " + keyEvent.getCode());

        if (null != keyEvent.getCode()) {
            switch (keyEvent.getCode()) {
                case RIGHT:
                    if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                        player.move(DirectionEnum.RIGHT, MovementTypeEnum.WALK);
                    } else if (keyEvent.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                        player.move(DirectionEnum.RIGHT, MovementTypeEnum.STOPED);
                    }
                    break;
                case LEFT:
                    if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                        player.move(DirectionEnum.LEFT, MovementTypeEnum.WALK);
                    } else if (keyEvent.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                        player.move(DirectionEnum.LEFT, MovementTypeEnum.STOPED);
                    }
                    break;
                case UP:
                    if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                        player.move(DirectionEnum.UP, MovementTypeEnum.WALK);
                    } else if (keyEvent.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                        player.move(DirectionEnum.UP, MovementTypeEnum.STOPED);
                    }
                    break;
                case DOWN:
                    if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                        player.move(DirectionEnum.DOWN, MovementTypeEnum.WALK);
                    } else if (keyEvent.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                        player.move(DirectionEnum.DOWN, MovementTypeEnum.STOPED);
                    }
                    break;
                case ESCAPE: //@TODO
                    if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                        player.move(DirectionEnum.LEFT, MovementTypeEnum.ATTACK);
                    } else if (keyEvent.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                        player.move(DirectionEnum.LEFT, MovementTypeEnum.STOPED);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
