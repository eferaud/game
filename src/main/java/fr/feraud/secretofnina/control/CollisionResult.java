/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.control;

import fr.feraud.secretofnina.model.Sprite;
import fr.feraud.secretofnina.model.Tile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eric
 */
public class CollisionResult {

    private final List<Tile> tilesInCollision;

    private final List<Sprite> spritesInCollision;

    private boolean outSideMapCollision;

    public CollisionResult() {
        tilesInCollision = new ArrayList<>();
        spritesInCollision = new ArrayList<>();
        outSideMapCollision = false;
    }

    public boolean isCollision() {
        return outSideMapCollision || isSpriteCollision() || tilesInCollision.size() > 0;
    }

    public boolean isOutSideMapCollision() {
        return outSideMapCollision;
    }

    public void setOutSideMapCollision(boolean outSideMapCollision) {
        this.outSideMapCollision = outSideMapCollision;
    }

    public void add(Tile tile) {
        tilesInCollision.add(tile);
    }

    public void add(Sprite sprite) {
        spritesInCollision.add(sprite);
    }

    public boolean isSpriteCollision() {
        return spritesInCollision.size() > 0;
    }

    public List<Sprite> getSpritesInCollision() {
        return spritesInCollision;
    }

}
