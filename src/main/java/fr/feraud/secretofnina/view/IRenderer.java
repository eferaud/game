/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view;

import fr.feraud.secretofnina.model.GameCamera;
import fr.feraud.secretofnina.view.fx.IFxEffect;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author eric
 * @param <T> Le type d'élements à rendre
 * @param <L> Le type de layer (node)
 */
public interface IRenderer<T, L extends Node> {

    /**
     *
     * @param element L'élement à afficher
     * @param layer La couche dans laquelle dessiner l'élément
     * @param fxEffect Effet FX à appliquer (peut être null)
     */
    public void render(T element, L layer, IFxEffect fxEffect);

    public L initLayer(T element, GameCamera gameCamera, Pane parent, int width, int height);

}
