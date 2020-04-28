/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.map;

import fr.feraud.secretofnina.model.GameCamera;
import fr.feraud.secretofnina.model.StageMap;
import fr.feraud.secretofnina.model.Tile;
import fr.feraud.secretofnina.view.IRenderer;
import fr.feraud.secretofnina.view.fx.IFxEffect;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;

/**
 * Les Renderer sont stateless
 *
 * @author eric
 */
public class DefaultMapRenderer implements IRenderer<StageMap, Canvas> {

    private GameCamera gameCamera;

    @Override
    public void render(StageMap stage, Canvas layer, IFxEffect fxEffect) {
        for (Tile tile : stage.getTiles(gameCamera)) {
            render(layer, stage, tile, fxEffect);
        }

    }

    @Override
    public Canvas initLayer(StageMap stage, GameCamera gameCamera, Pane parent, int width, int height) {
        this.gameCamera = gameCamera;
        Canvas layer = new Canvas(width, height);
        layer.getGraphicsContext2D().setFill(new ImagePattern(stage.getBackground()));
        parent.getChildren().add(layer);

        return layer;
    }

    private void render(Canvas layer, StageMap stage, Tile tile, IFxEffect fxEffect) {
        Image tileImage = getImage(stage, tile.getTilesetX(), tile.getTilesetY(), fxEffect);

        layer.getGraphicsContext2D().drawImage(tileImage, 0, 0, tileImage.getWidth(), tileImage.getHeight(), tile.getMapPositionX(), tile.getMapPositionY(), tileImage.getWidth(), tileImage.getHeight());
    }

    private Image getImage(StageMap stage, int tilesetX, int tilesetY, IFxEffect fxEffect) {
        Image originalImage = stage.getTilesImage().get(new Point2D(tilesetX, tilesetY));

        //On applique les effet à l'image si il y en a
        Image image;
        if (fxEffect != null) {
            image = fxEffect.applyEffect(originalImage, 0); //@TODO : numéro de frame d'animation à donner
        } else {
            image = originalImage;
        }

        return image;
    }
}
