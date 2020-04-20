/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model.json;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author eric
 */
public class StageMapJson {

    //Pour la persistence
    private String name;
    private String tileSet;
    private Integer width;
    private Integer height;
    private List<TileJson> tiles;
    private List<SpriteJson> ennemis;
    private String backGroundImage;

    public StageMapJson() {
    }

    public StageMapJson(String mapFilePath) {
        this.name = mapFilePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTileSet() {
        return tileSet;
    }

    public void setTileSet(String tileSet) {
        this.tileSet = tileSet;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<TileJson> getTiles() {
        return tiles;
    }

    public void setTiles(List<TileJson> tiles) {
        this.tiles = tiles;
    }

    public List<SpriteJson> getEnnemis() {
        return ennemis;
    }

    public void setEnnemis(List<SpriteJson> ennemis) {
        this.ennemis = ennemis;
    }

    public String getBackGroundImage() {
        return backGroundImage;
    }

    public void setBackGroundImage(String backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    /**
     *
     * @param gx Position x sur la map en pixel
     * @param gy Position y sur la map en pixel
     * @return La tuile suivant sa position sur la map en pixel, null si elle
     * n'existe pas
     */
    public TileJson getTile(int gx, int gy) {
        TileJson search = new TileJson();
        search.setPx(gx);
        search.setPy(gy);
        int index = tiles.indexOf(search);
        if (index >= 0) {
            return tiles.get(index);
        }
        return null;
    }

    /**
     * Ajoute la tile dans la map si elle n'existe pas déjà, ou la met à jour
     *
     * @param ut
     */
    public void saveOrUpdateTile(TileJson ut) {
        TileJson search = getTile(ut.getPx(), ut.getPy());
        if (search != null) {
            search.copy(ut);
        } else {
            tiles.add(ut);
        }
    }

    public void removeTile(TileJson tileJson) {
        if (tileJson != null) {
            Iterator<TileJson> iter = tiles.iterator();
            while (iter.hasNext()) {
                TileJson t = iter.next();
                if (t != null && t.equals(tileJson)) {
                    iter.remove();
                }
            }
        }
    }

}
