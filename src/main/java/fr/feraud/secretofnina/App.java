package fr.feraud.secretofnina;

import fr.feraud.secretofnina.control.GameCollisionEngine;
import fr.feraud.secretofnina.control.GameIAEngine;
import fr.feraud.secretofnina.control.GamePlayLoop;
import fr.feraud.secretofnina.control.IGameCollisionEngine;
import fr.feraud.secretofnina.control.PlayerEventHandler;
import fr.feraud.secretofnina.gamepad.GamePadController;
import fr.feraud.secretofnina.model.GameCamera;
import fr.feraud.secretofnina.model.StageMap;
import fr.feraud.secretofnina.model.json.StageMapSerializer;
import fr.feraud.secretofnina.view.GameRenderEngine;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 *
 * @TODO utiliser tilePane ?
 */
public class App extends Application {

    @Override
    //where the application will be displayed
    public void start(Stage stage) throws Exception {
        StageMapSerializer.test();
        ApplicationParameters applicationParameters = new ApplicationParameters();
        StageMap map = new StageMap(StageMap.MAP1);

        GameCamera gameCamera = new GameCamera(0, 0, applicationParameters.getWidth(), applicationParameters.getHeight());

        GameRenderEngine gameRenderEngine = new GameRenderEngine(stage, applicationParameters, map, gameCamera);
        gameRenderEngine.attachHandler(new PlayerEventHandler(map.getPlayer()));

        GameIAEngine gameIAEngine = new GameIAEngine(map.getEnnemies());
        IGameCollisionEngine gameCollisionEngine = new GameCollisionEngine(map, gameCamera);

        GamePadController gamePadController = new GamePadController(map.getPlayer());

        stage.show();

        GamePlayLoop gamePlayLoop = new GamePlayLoop(gameRenderEngine, gameIAEngine, gameCollisionEngine, gamePadController);
        gamePlayLoop.start();

    }

    public static void main(String[] args) {
        launch();
    }

}
