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
public class Lapin extends Sprite {

    private static final int WIDTH = 31;
    private static final int HEIGHT = 27;
    private static final int LIFE_POINT = 27;

    public Lapin(Integer x, Integer y) {
        super(WIDTH, HEIGHT, LIFE_POINT);
        super.setPosition(x, y);
    }
}
