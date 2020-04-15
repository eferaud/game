/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.control;

import fr.feraud.secretofnina.model.Sprite;
import fr.feraud.secretofnina.model.StageMap;
import fr.feraud.secretofnina.model.Tile;
import java.util.logging.Logger;

/**
 *
 * @author eric
 */
public class GameCollisionEngine implements IGameCollisionEngine {

    private final static Logger LOG = Logger.getLogger(GameCollisionEngine.class.getName());
    private StageMap map;

    public GameCollisionEngine(StageMap map) {
        LOG.info("GameCollisionEngine");
        this.map = map;
    }

    /**
     *
     * @param time Le temps en milliseconde depuis le dernier appel
     */
    @Override
    public void checkCollision(double time) {
        map.getEnnemies().forEach((player) -> {
            processCollision(player, time);
        });
        processCollision(map.getPlayer(), time);
    }

    private void processCollision(Sprite player, double time) {
        player.update(time);
        if (isCollisions(player, time)) {
            player.setInCollision(true);
            player.rollback(time);
            player.eraseVelocity();
        } else {
            player.setInCollision(false);
        }
    }

    /**
     * Check si il y a une collision entre le player et tous les autres
     *
     * @param player
     * @param time
     */
    private boolean isCollisions(Sprite player, double time) {
        for (Sprite other : map.getEnnemies()) {
            if (player != other) { //pas de self collision
                if (broadCollision(player, other) && narrowSpriteCollision(player, other)) {
                    return true;
                }
            }
        }
        for (Tile tile : map.getTiles()) {
            if (broadCollision(player, tile) && narrowTileCollision(player, tile)) {
                return true;
            }
        }

        return false;
    }

    //Collision étroite
    private boolean narrowSpriteCollision(Sprite sprite, Sprite other) {
        if (sprite.getClipping() != null) {
            if (sprite.getClipping().stream().anyMatch((point2D) -> (other.getClipping().contains(point2D)))) {
                return true;
            }
        }
        return false;
    }

    //Collision étroite
    private boolean narrowTileCollision(Sprite sprite, Tile other) {
        if (sprite.getClipping() != null) {
            if (sprite.getClipping().stream().anyMatch((point2D) -> (other.getBoundary().contains(point2D)))) {
                return true;
            }
        }
        return false;
    }

    //Collision large
    private boolean broadCollision(Tile sprite, Tile other) {
        return other.getBoundary().intersects(sprite.getBoundary());
    }
}
