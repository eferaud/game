/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view;

import fr.feraud.secretofnina.ApplicationParameters;
import fr.feraud.secretofnina.control.PlayerEventHandler;
import fr.feraud.secretofnina.model.GameCamera;
import fr.feraud.secretofnina.model.Lapin;
import fr.feraud.secretofnina.model.Randy;
import fr.feraud.secretofnina.model.Sprite;
import fr.feraud.secretofnina.model.StageMap;
import fr.feraud.secretofnina.model.Toto;
import fr.feraud.secretofnina.view.map.DefaultMapRenderer;
import fr.feraud.secretofnina.view.sprite.LapinRenderer;
import fr.feraud.secretofnina.view.sprite.RandyRenderer;
import fr.feraud.secretofnina.view.sprite.TotoRenderer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
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
    private final Canvas mapLayer;

    private GameCamera gameCamera;

    static {
        RENDERERS.put(Lapin.class, new LapinRenderer());
        RENDERERS.put(Randy.class, new RandyRenderer());
        RENDERERS.put(Toto.class, new TotoRenderer());

        RENDERERS.put(StageMap.class, new DefaultMapRenderer());
    }

    public GameRenderEngine(Stage stage, ApplicationParameters applicationParameters, StageMap map, GameCamera gameCamera) {
        this.gameCamera = gameCamera;
        this.map = map;

        root = new AnchorPane();
        root.setBackground(Background.EMPTY);

        //one container of Nodes that compose one “page” of your application
        Scene scene = new Scene(root, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("SecretOfNina " + applicationParameters.getWidth() + "X" + applicationParameters.getHeight());
        //stage.initStyle(javafx.stage.StageStyle.TRANSPARENT); @TODO à activer à la fin

        //Init layer de la MAP
        this.mapLayer = (Canvas) RENDERERS.get(map.getClass()).initLayer(map, gameCamera, root, applicationParameters.getWidth(), applicationParameters.getHeight());
        //Init layer des sprites
        this.spriteLayer = (Canvas) RENDERERS.get(map.getPlayer().getClass()).initLayer(map.getPlayer(), gameCamera, root, applicationParameters.getWidth(), applicationParameters.getHeight());

        root.setFocusTraversable(true);
        root.requestFocus();

    }

    /**
     *
     * @param time Le temps en milliseconde depuis le dernier appel
     */
    public synchronized void render(double time) {
        GraphicsContext spitesContext = spriteLayer.getGraphicsContext2D();
        GraphicsContext mapContext = mapLayer.getGraphicsContext2D();

        //Réinit du layer des sprites
        spitesContext.clearRect(gameCamera.getOffsetX(), gameCamera.getOffsetY(), gameCamera.getWidth(), gameCamera.getHeight());

        //Réinit du layer de la map
        mapContext.clearRect(gameCamera.getOffsetX(), gameCamera.getOffsetY(), gameCamera.getWidth(), gameCamera.getHeight());

        //Render de la map
        render(this.map, time);

        //Render des ennemis
        this.map.getEnnemies().forEach((player) -> {
            render(player, time);
        });
        //Render du joueur
        render(this.map.getPlayer(), time);

        processScrolling();

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

    public Canvas getSpriteLayer() {
        return spriteLayer;
    }

    public Canvas getMapLayer() {
        return mapLayer;
    }

    public Pane getRoot() {
        return root;
    }

    private void processScrolling() {
        GraphicsContext spitesContext = spriteLayer.getGraphicsContext2D();
        GraphicsContext mapContext = mapLayer.getGraphicsContext2D();

        boolean canScrollLeft = gameCamera.getOffsetX() >= 0;
        boolean canScrollRight = (gameCamera.getOffsetX() + gameCamera.getWidth()) <= map.getWidth();

        boolean canScrollUp = gameCamera.getOffsetY() >= 0;
        boolean canScrollDown = (gameCamera.getOffsetY() + gameCamera.getHeight()) <= map.getHeight();

        //Translation droite
        //Si position sur la mapX du player > (positionX de la camera + la largeur de la camera /2)
        if (canScrollRight && this.map.getPlayer().getMapPositionX() > (gameCamera.getOffsetX() + gameCamera.getWidth() * (1d - GameCamera.SCROLL_TRIGGER_THRESHOLD))) {
            spitesContext.translate(-3, 0);
            mapContext.translate(-3, 0);
            gameCamera.translateX(3);
        }

        //Translation gauche
        //Si position sur la mapX du player < (positionX de la camera + la largeur de la camera /2)
        if (canScrollLeft && this.map.getPlayer().getMapPositionX() < (gameCamera.getOffsetX() + gameCamera.getWidth() * GameCamera.SCROLL_TRIGGER_THRESHOLD)) {
            spitesContext.translate(3, 0);
            mapContext.translate(3, 0);
            gameCamera.translateX(-3);
        }

        //Translation bas
        if (canScrollDown && this.map.getPlayer().getMapPositionY() > (gameCamera.getOffsetY() + gameCamera.getHeight() * (1d - GameCamera.SCROLL_TRIGGER_THRESHOLD))) {
            spitesContext.translate(0, -3);
            mapContext.translate(0, -3);
            gameCamera.translateY(3);
        }

        //Translation haut
        if (canScrollUp && this.map.getPlayer().getMapPositionY() < (gameCamera.getOffsetY() + gameCamera.getHeight() * GameCamera.SCROLL_TRIGGER_THRESHOLD)) {
            spitesContext.translate(0, 3);
            mapContext.translate(0, 3);
            gameCamera.translateY(-3);
        }

    }

}
