/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view;

import fr.feraud.secretofnina.model.Tile;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author eric
 * @param <T>
 */
public interface IRenderer<T extends Tile> {

    public void render(T element, GraphicsContext graphicsContext);

}
