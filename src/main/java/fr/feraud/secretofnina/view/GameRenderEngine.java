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
import fr.feraud.secretofnina.view.map.DefaultMapRenderer;
import fr.feraud.secretofnina.view.sprite.LapinRenderer;
import fr.feraud.secretofnina.view.sprite.RandyRenderer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author eric
 */
public class GameRenderEngine {

    private final static Logger LOG = Logger.getLogger(GameRenderEngine.class.getName());

    private final Pane root;

    private final StageMap map;
    private final static Map<Class, IRenderer> RENDERERS = new HashMap<>();

    private final Canvas spriteLayer;
    private final Node mapLayer;

    static {
        RENDERERS.put(Lapin.class, new LapinRenderer());
        RENDERERS.put(Randy.class, new RandyRenderer());

        RENDERERS.put(StageMap.class, new DefaultMapRenderer());
    }

    public GameRenderEngine(Stage stage, ApplicationParameters applicationParameters, StageMap map) {
        root = new Pane();
        root.setBackground(Background.EMPTY);

        //one container of Nodes that compose one “page” of your application
        Scene theScene = new Scene(root, Color.BLACK);
        stage.setScene(theScene);
        stage.setTitle("SecretOfNina " + applicationParameters.getWidth() + "X" + applicationParameters.getHeight());
        //stage.initStyle(javafx.stage.StageStyle.TRANSPARENT); @TODO à activer à la fin

        //Init layer de la MAP
        this.mapLayer = RENDERERS.get(map.getClass()).initLayer(map, root, applicationParameters.getWidth(), applicationParameters.getHeight());
        //Init layer des sprites
        this.spriteLayer = (Canvas) RENDERERS.get(map.getPlayer().getClass()).initLayer(map.getPlayer(), root, applicationParameters.getWidth(), applicationParameters.getHeight());

        root.setFocusTraversable(true);
        root.requestFocus();

        this.map = map;
    }

    /**
     *
     * @param time Le temps en milliseconde depuis le dernier appel
     */
    public synchronized void render(double time) {
        GraphicsContext context = spriteLayer.getGraphicsContext2D();
        //Réinit du layer des sprites
        context.clearRect(0, 0, spriteLayer.getWidth(), spriteLayer.getHeight());

        //Render de la map
        render(this.map, time);

        //Render des ennemis
        this.map.getEnnemies().forEach((player) -> {
            render(player, time);
        });
        //Render du joueur
        render(this.map.getPlayer(), time);

    }

    private void render(Sprite element, double time) {
        RENDERERS.get(element.getClass()).render(element, this.spriteLayer);
    }

    private void render(StageMap map, double time) {
        RENDERERS.get(map.getClass()).render(map, this.mapLayer);
    }

    public void attachHandler(PlayerEventHandler playerEventHandler) {
        root.setOnKeyPressed(playerEventHandler);
        root.setOnKeyReleased(playerEventHandler);
    }

}
