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
import fr.feraud.secretofnina.model.StageMap;
import fr.feraud.secretofnina.view.sprite.DefaultSpriteRenderer;
import fr.feraud.secretofnina.view.sprite.LapinRenderer;
import fr.feraud.secretofnina.view.sprite.RandyRenderer;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author eric
 */
public class GameRenderEngine {

    private final Pane root;
    private final static boolean SHOW_POSITION = false;

    private final StageMap map;
    private final static Map<Class, DefaultSpriteRenderer> MAP_RENDERER = new HashMap<>();

    private Rectangle backGroundLayer;
    private final Canvas spriteLayer;

    static {
        MAP_RENDERER.put(Lapin.class, new LapinRenderer());
        MAP_RENDERER.put(Randy.class, new RandyRenderer());
    }

    public GameRenderEngine(Stage stage, ApplicationParameters applicationParameters, StageMap map) {
        root = new Pane();
        root.setBackground(Background.EMPTY);
        //one container of Nodes that compose one “page” of your application
        Scene theScene = new Scene(root, Color.BLACK);
        stage.setScene(theScene);
        stage.setTitle("SecretOfNina " + applicationParameters.getWidth() + "X" + applicationParameters.getHeight());
        //stage.initStyle(javafx.stage.StageStyle.TRANSPARENT); @TODO à activer à la fin
        this.backGroundLayer = new Rectangle(applicationParameters.getWidth(), applicationParameters.getHeight(), new ImagePattern(map.getBackground()));

        root.getChildren().add(backGroundLayer);

        this.spriteLayer = new Canvas(applicationParameters.getWidth(), applicationParameters.getHeight());
        root.getChildren().add(spriteLayer);
        root.setFocusTraversable(true);
        root.requestFocus();
        this.map = map;
    }

    /**
     *
     * @param time Le temps en milliseconde depuis le dernier appel
     */
    public void render(double time) {
        GraphicsContext context = spriteLayer.getGraphicsContext2D();

        context.clearRect(0, 0, spriteLayer.getWidth(), spriteLayer.getHeight());

        //On suppprime tous les enfants
        /* Node layer1 = root.getChildren().get(0);
        root.getChildren().clear();
        root.getChildren().add(layer1);*/
        this.map.getEnnemies().forEach((player) -> {
            render(player, time);
        });
        render(this.map.getPlayer(), time);

        //@TODO : render de la MAP
    }

    private void render(Sprite player, double time) {

        MAP_RENDERER.get(player.getClass()).render(player, this.spriteLayer.getGraphicsContext2D());

        if (SHOW_POSITION) {

            Color color = Color.CYAN;
            if (player.getClass().equals(Randy.class)) {
                color = Color.RED;
            }

            //haut gauche
            Text text = new Text(player.getBoundary().getMinX() + "x" + player.getBoundary().getMinY());
            text.setFill(color);
            text.setFont(Font.font(12));
            text.setX(player.getBoundary().getMinX() - 60);
            text.setY(player.getBoundary().getMinY());
            root.getChildren().add(text);

            //haut droite
            Text text2 = new Text(player.getBoundary().getMaxX() + "x" + player.getBoundary().getMinY());
            text2.setFill(color);
            text2.setFont(Font.font(12));
            text2.setX(player.getBoundary().getMaxX());
            text2.setY(player.getBoundary().getMinY());
            root.getChildren().add(text2);

            //bas gauche
            Text text3 = new Text(player.getBoundary().getMinX() + "x" + player.getBoundary().getMaxY());
            text3.setFill(color);
            text3.setFont(Font.font(12));
            text3.setX(player.getBoundary().getMinX() - 60);
            text3.setY(player.getBoundary().getMaxY());
            root.getChildren().add(text3);

            //bas droite
            Text text4 = new Text(player.getBoundary().getMaxX() + "x" + player.getBoundary().getMaxY());
            text4.setFill(color);
            text4.setFont(Font.font(12));
            text4.setX(player.getBoundary().getMaxX());
            text4.setY(player.getBoundary().getMaxY());
            root.getChildren().add(text4);

        }
    }

    public void attachHandler(PlayerEventHandler playerEventHandler) {
        root.setOnKeyPressed(playerEventHandler);
        root.setOnKeyReleased(playerEventHandler);
    }
}
