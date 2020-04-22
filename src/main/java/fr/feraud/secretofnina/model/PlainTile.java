/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

/**
 *
 * @author eric
 */
@Deprecated
public class PlainTile extends Tile {

    public PlainTile(int tilesetX, int tilesetY, int width, int height, boolean transparency) {
        super(tilesetX, tilesetY, width, height, true, transparency);
    }

}
