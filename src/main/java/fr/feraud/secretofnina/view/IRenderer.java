/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author eric
 * @param <T> Le type d'élements à rendre
 * @param <L> Le type de layer (node)
 */
public interface IRenderer<T, L extends Node> {

    public void render(T element, L layer);

    public L initLayer(T element, Pane parent, int width, int height);

}
