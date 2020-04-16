/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.tile;

import javafx.scene.image.Image;

/**
 *
 * @author eric
 */
public class PlainTileRenderer extends DefaultTileRenderer {

    public PlainTileRenderer() {
    }

    @Override
    protected Image getImage() {
        return new Image("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\lapin3.png");
    }

}
