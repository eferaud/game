/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model.json;

import java.util.Objects;

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

    /**
     *
     * @param px Position sur la MAP en grile de 16x16
     * @param py Position sur la MAP en grile de 16x16
     * @param x Position dans le tileset en nombre
     * @param y Position dans le tileset en nombre
     * @param p Engenadre une collision dans le jeu
     * @param t A du fond transparent
     */
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.px);
        hash = 97 * hash + Objects.hashCode(this.py);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TileJson other = (TileJson) obj;
        if (!Objects.equals(this.px, other.px)) {
            return false;
        }
        if (!Objects.equals(this.py, other.py)) {
            return false;
        }
        return true;
    }

    public void copy(TileJson copy) {
        this.x = copy.getX();
        this.y = copy.getY();
        this.p = copy.getP();
        this.t = copy.getT();
        this.px = copy.getPx();
        this.py = copy.getPy();
    }

}
