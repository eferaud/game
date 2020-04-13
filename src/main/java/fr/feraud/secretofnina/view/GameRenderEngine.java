/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view;

import fr.feraud.secretofnina.ApplicationParameters;
import fr.feraud.secretofnina.control.PlayerEventHandler;
import fr.feraud.secretofnina.model.Lapin;
import fr.feraud.secretofnina.model.Randy;
import fr.feraud.secretofnina.model.Sprite;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author eric
 */
public class GameRenderEngine {

    private final Pane root;

    private final List<Sprite> players; //@TODO il faut le niveau en fait
    private final static Map<Class, DefaultSpriteRenderer> MAP_RENDERER = new HashMap<>();

    static {
        MAP_RENDERER.put(Lapin.class, new LapinRenderer());
        MAP_RENDERER.put(Randy.class, new RandyRenderer());
    }

    public GameRenderEngine(Stage stage, ApplicationParameters applicationParameters, List<Sprite> players) {
        root = new StackPane();
        root.setBackground(Background.EMPTY);
        //one container of Nodes that compose one “page” of your application
        Scene theScene = new Scene(root, Color.BLACK);
        stage.setScene(theScene);
        stage.setTitle("SecretOfNina " + applicationParameters.getWidth() + "X" + applicationParameters.getHeight());
        //stage.initStyle(javafx.stage.StageStyle.TRANSPARENT); @TODO à activer à la fin

        Canvas canvas = new Canvas(applicationParameters.getWidth(), applicationParameters.getHeight());
        root.getChildren().add(canvas);
        root.setFocusTraversable(true);
        root.requestFocus();
        this.players = players;
    }

    /**
     *
     * @param time Le temps en milliseconde depuis le dernier appel
     */
    public void render(double time) {
        Canvas canvas = (Canvas) root.getChildren().get(0);
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        players.forEach((player) -> {
            render(player, time);
        });

    }

    private void render(Sprite player, double time) {
        player.update(time);
        MAP_RENDERER.get(player.getClass()).render(player, getGraphicsContext());
    }

    public GraphicsContext getGraphicsContext() {
        Canvas canvas = (Canvas) root.getChildren().get(0);
        return canvas.getGraphicsContext2D();
    }

    public void attachHandler(PlayerEventHandler playerEventHandler) {
        root.setOnKeyPressed(playerEventHandler);
        root.setOnKeyReleased(playerEventHandler);
    }
}
