/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model.json;

/**
 *
 * @author eric
 */
public class TileJson {

    private Integer x;
    private Integer y;

    private Integer px;
    private Integer py;
    private Boolean p; //collision
    private Boolean t; // gestion tranparence

    public TileJson() {
    }

    public TileJson(Integer px, Integer py, Integer x, Integer y, Boolean p, Boolean t) {
        this.x = x;
        this.y = y;
        this.p = p;
        this.t = t;
        this.px = px;
        this.py = py;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Boolean getP() {
        return p;
    }

    public void setP(Boolean p) {
        this.p = p;
    }

    public Boolean getT() {
        return t;
    }

    public void setT(Boolean t) {
        this.t = t;
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    public Integer getPy() {
        return py;
    }

    public void setPy(Integer py) {
        this.py = py;
    }

}
