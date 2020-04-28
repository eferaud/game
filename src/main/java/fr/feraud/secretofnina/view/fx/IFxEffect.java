/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.fx;

import javafx.scene.image.Image;

/**
 *
 * @author eric
 */
public interface IFxEffect {

    Image applyEffect(Image image, int loopCounter);

}
