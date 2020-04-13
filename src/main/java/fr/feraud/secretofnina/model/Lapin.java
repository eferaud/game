/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import fr.feraud.secretofnina.view.DefaultSpriteRenderer;
import fr.feraud.secretofnina.view.LapinRenderer;

/**
 *
 * @author eric
 */
public class Lapin extends Sprite {

    private static final int WIDTH = 31;
    private static final int HEIGHT = 30;
    private static final int LIFE_POINT = 27;

    public Lapin(int x, int y) {
        super(WIDTH, HEIGHT, LIFE_POINT);
        super.setPosition(x, y);
    }

    //TODO
    /* @Override
    protected Rectangle2D getSpritePosition() {

        double x = super.getPositionX();

        double y = super.getPositionY();

        int width = WIDTH;

        int height = HEIGHT;

        switch (super.getDirection()) {
            case LEFT:
                width = -WIDTH;

                break;
            case RIGHT:
                width = WIDTH;
                //x = x + 10;
                break;

        }
        return new Rectangle2D(x, y, width, height);
    }*/
    @Override
    public DefaultSpriteRenderer<? extends Sprite> getRenderedClass() {
        return new LapinRenderer();
    }
}
