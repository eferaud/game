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
import fr.feraud.secretofnina.utils.ImageUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
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
    private List<Tile> tiles;
    private final Map<Point2D, Image> tilesImage;

    public StageMap(String mapPath) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        StageMapJson datas = StageMapSerializer.deserialize(mapPath);
        tiles = new ArrayList();
        for (TileJson tileJson : datas.getTiles()) {
            PlainTile t = new PlainTile(tileJson.getX(), tileJson.getY(), 16, 16);
            t.setPosition(tileJson.getPx(), tileJson.getPy());
            tiles.add(t);
        }
        ennemies = new ArrayList();
        for (SpriteJson ennmieJson : datas.getEnnemis()) {
            Sprite sprite = (Sprite) Class.forName(SPRITE_PACKAGE + ennmieJson.getName()).getDeclaredConstructor(Integer.class, Integer.class).newInstance(200, 200);
            ennemies.add(sprite);
        }

        player = new Randy(100, 100);
        background = new Image(IMG_ROOT_PATH + datas.getBackGroundImage());
        tilesImage = ImageUtils.getTilesFromTileset(IMG_ROOT_PATH + datas.getTileSet(), 16, 16);
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

    public Map<Point2D, Image> getTilesImage() {
        return tilesImage;
    }

}
