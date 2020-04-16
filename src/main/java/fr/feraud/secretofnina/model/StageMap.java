/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import fr.feraud.secretofnina.model.json.SpriteJson;
import fr.feraud.secretofnina.model.json.StageMapJson;
import fr.feraud.secretofnina.model.json.StageMapSerializer;
import fr.feraud.secretofnina.model.json.TileJson;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author eric
 */
public class StageMap {

    private final static String IMG_ROOT_PATH = "file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\";
    private final static String SPRITE_PACKAGE = "fr.feraud.secretofnina.model.";
    public final static String MAP1 = "map1.txt";

    private List<Sprite> ennemies;
    private Image background;
    private Sprite player;
    private List<Tile> tiles; //13*15 cases.

    public StageMap(String mapPath) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        StageMapJson json = StageMapSerializer.deserialize(mapPath);
        tiles = new ArrayList();
        for (TileJson tileJson : json.getTiles()) {
            tiles.add(new PlainTile(0, 0, 16, 16));
        }
        ennemies = new ArrayList();
        for (SpriteJson ennmieJson : json.getEnnemis()) {
            Sprite sprite = (Sprite) Class.forName(SPRITE_PACKAGE + ennmieJson.getName()).getDeclaredConstructor(Integer.class, Integer.class).newInstance(200, 200);
            ennemies.add(sprite);
        }

        player = new Randy(100, 100);
        background = new Image(IMG_ROOT_PATH + json.getBackGroundImage());

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
