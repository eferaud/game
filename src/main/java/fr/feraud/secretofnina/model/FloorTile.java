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
public class FloorTile extends Tile {

    public FloorTile(int tilesetX, int tilesetY, int width, int height) {
        super(tilesetX, tilesetY, width, height, false, false);
    }

}
