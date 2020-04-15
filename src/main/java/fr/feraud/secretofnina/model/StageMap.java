/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author eric
 */
public class StageMap {

    private List<Sprite> ennemies;
    private Image background;
    private Sprite player;
    private List<Tile> tiles;

    public StageMap() {
        ennemies = new ArrayList<>();
        ennemies.add(new Lapin(200, 200));
        ennemies.add(new Lapin(300, 200));
        ennemies.add(new Lapin(200, 200));
        ennemies.add(new Lapin(500, 200));
        ennemies.add(new Lapin(200, 300));
        ennemies.add(new Lapin(300, 400));
        ennemies.add(new Lapin(200, 500));
        player = new Randy(100, 100);
        background = new Image("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\background.png");
        tiles = new ArrayList();

    }

    public List<Sprite> getEnnemies() {
        return ennemies;
    }

    public void setEnnemies(List<Sprite> ennemies) {
        this.ennemies = ennemies;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public Sprite getPlayer() {
        return player;
    }

    public void setPlayer(Sprite player) {
        this.player = player;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

}