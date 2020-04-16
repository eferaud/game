/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model.json;

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

}
