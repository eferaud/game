/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina;

import fr.feraud.secretofnina.control.GameIAEngine;
import fr.feraud.secretofnina.control.IGameCollisionEngine;
import fr.feraud.secretofnina.gamepad.GamePadController;
import fr.feraud.secretofnina.view.GameRenderEngine;
import javafx.animation.AnimationTimer;
import javafx.util.Duration;

/**
 *
 * @author eric
 */
public class GamePlayLoop extends AnimationTimer {

    private final GameRenderEngine gameRenderEngine;
    private long previousNow = 0l;
    private final static int FRAME_RATE = 60;
    private final GameIAEngine gameIAEngine;
    private final IGameCollisionEngine gameCollisionEngine;
    private GamePadController gamePadController;

    GamePlayLoop(GameRenderEngine gameRenderEngine, GameIAEngine gameIAEngine, IGameCollisionEngine gameCollisionEngine, GamePadController gamePadController) {
        this.gameRenderEngine = gameRenderEngine;
        this.gameIAEngine = gameIAEngine;
        this.gameCollisionEngine = gameCollisionEngine;
        this.gamePadController = gamePadController;
    }

    @Override
    public void handle(long now) {
        if (previousNow == 0l) {
            previousNow = now;
        }
        Duration d1 = new Duration(now / 1000000);
        Duration d2 = new Duration(previousNow / 1000000);

        double time = d1.subtract(d2).toMillis();

        if (time > (1000 / FRAME_RATE)) {
            gamePadController.run();
            this.gameIAEngine.play(time);
            this.gameCollisionEngine.checkCollision(time);
            gameRenderEngine.render(time);
            previousNow = now;
        }

    }

}
