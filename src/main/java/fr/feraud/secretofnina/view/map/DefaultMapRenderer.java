/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.map;

import fr.feraud.secretofnina.model.StageMap;
import fr.feraud.secretofnina.model.Tile;
import fr.feraud.secretofnina.view.IRenderer;
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

    @Override
    public void render(StageMap stage, Canvas layer) {
        for (Tile tile : stage.getTiles()) {
            render(layer, stage, tile, 0);
        }

    }

    @Override
    public Canvas initLayer(StageMap stage, Pane parent, int width, int height) {
        Canvas layer = new Canvas(width, height);
        layer.getGraphicsContext2D().setFill(new ImagePattern(stage.getBackground()));
        parent.getChildren().add(layer);
        return layer;
    }

    private void render(Canvas layer, StageMap stage, Tile tile, double time) {
        Image tileImage = stage.getTilesImage().get(new Point2D(tile.getTilesetX(), tile.getTilesetY()));
        layer.getGraphicsContext2D().drawImage(tileImage, 0, 0, tileImage.getWidth(), tileImage.getHeight(), tile.getMapPositionX(), tile.getMapPositionY(), tileImage.getWidth(), tileImage.getHeight());
    }
}
