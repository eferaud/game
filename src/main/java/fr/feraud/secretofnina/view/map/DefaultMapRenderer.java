/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.map;

import fr.feraud.secretofnina.model.StageMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Les Renderer sont stateless
 *
 * @author eric
 */
public abstract class DefaultMapRenderer {

    public void render(StageMap stage, GraphicsContext graphicsContext) {

    }

    public abstract Image getBackgroundImage();

}
