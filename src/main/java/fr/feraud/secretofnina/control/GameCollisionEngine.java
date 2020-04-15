/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.control;

import fr.feraud.secretofnina.model.DirectionEnum;
import fr.feraud.secretofnina.model.MovementTypeEnum;
import fr.feraud.secretofnina.model.Sprite;
import fr.feraud.secretofnina.model.StageMap;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author eric
 */
public class GameCollisionEngine implements IGameCollisionEngine {

    private StageMap map;

    public GameCollisionEngine(StageMap map) {
        this.map = map;
    }

    /**
     *
     * @param time Le temps en milliseconde depuis le dernier appel
     */
    @Override
    public void checkCollision(double time) {
        map.getEnnemies().forEach((player) -> {
            checkCollision(player, time);
        });
        checkCollision(map.getPlayer(), time);
    }

    /**
     * Check si il y a une collision entre le player et tous les autres
     *
     * @param player
     * @param time
     * @return La liste des directions qui sont en collision (non dédoublonnée)
     */
    private List<DirectionEnum> getCollisions(Sprite player, double time) {
        List<DirectionEnum> collisions = new ArrayList<>();
        for (Sprite other : map.getEnnemies()) {
            if (player != other) { //pas de self collision
                if (intersects(player, other)) {
                    collisions.addAll(getIntersectionSide(player, other));
                }
            }
        }
        return collisions;
    }

    private boolean intersects(Sprite sprite, Sprite other) {
        if (sprite.getClipping() != null) {
            for (Point2D point2D : sprite.getClipping()) {
                if (other.getClipping().contains(point2D)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean intersects2(Sprite sprite, Sprite other) {
        return other.getBoundary().intersects(sprite.getBoundary());
    }

    private List<DirectionEnum> getIntersectionSide(Sprite sprite, Sprite other) {

        List<DirectionEnum> collisions = new ArrayList<>();

        //@TODO marche bof
        if (intersects(sprite, other) && !sprite.getMovementType().equals(MovementTypeEnum.STOPED)) {
            collisions.add(other.getDirection());
        }

        return collisions;
    }

    private void checkCollision(Sprite player, double time) {
        player.update(time);
        List<DirectionEnum> collisions = getCollisions(player, time);
        if (!collisions.isEmpty()) {
            player.setInCollision(true);
            player.rollback(time);
            player.eraseVelocity();
        } else {
            player.setInCollision(false);
        }
    }

}
