/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

/**
 *
 * @author eric
 */
public class Toto extends Sprite {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 28;
    private static final int LIFE_POINT = 50;

    public Toto(Integer x, Integer y) {
        super(WIDTH, HEIGHT, LIFE_POINT);
        super.setPosition(x, y);
    }

}
