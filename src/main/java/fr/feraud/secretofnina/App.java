package fr.feraud.secretofnina;

import fr.feraud.secretofnina.control.PlayerEventHandler;
import fr.feraud.secretofnina.model.Lapin;
import fr.feraud.secretofnina.model.Randy;
import fr.feraud.secretofnina.model.Sprite;
import fr.feraud.secretofnina.view.GameRenderEngine;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 *
 * @TODO utiliser tilePane ?
 */
public class App extends Application {

    private List<Sprite> players = new ArrayList<>();

    @Override
    //where the application will be displayed
    public void start(Stage stage) {

        ApplicationParameters applicationParameters = new ApplicationParameters();

        players.add(new Randy(100, 180));
        players.add(new Lapin(200, 280));

        GameRenderEngine gameRenderEngine = new GameRenderEngine(stage, applicationParameters, players);
        gameRenderEngine.attachHandler(new PlayerEventHandler(players.get(0)));

        stage.show();

        GamePlayLoop gamePlayLoop = new GamePlayLoop(gameRenderEngine);
        gamePlayLoop.start();

    }

    public static void main(String[] args) {
        launch();
    }

}
