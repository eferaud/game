/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.tile;

import fr.feraud.secretofnina.model.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author eric
 */
public abstract class DefaultTileRenderer {

    public void render(Tile tile, GraphicsContext graphicsContext) {
        //@param dx the destination rectangle's X coordinate position.
        double dx = tile.getPositionX();

        //@param dy the destination rectangle's Y coordinate position.
        double dy = tile.getPositionY();

        //@param dw the destination rectangle's width.
        double dw = tile.getWidth();

        //@param dh the destination rectangle's height.
        double dh = tile.getHeight();

        graphicsContext.drawImage(getImage(), 0, 0, tile.getWidth(), tile.getHeight(), dx, dy, dw, dh);
    }

    protected abstract Image getImage();

}
