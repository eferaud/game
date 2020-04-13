/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import fr.feraud.secretofnina.view.DefaultSpriteRenderer;
import fr.feraud.secretofnina.view.RandyRenderer;

/**
 *
 * @author eric
 */
public class Randy extends Sprite {

    private static final int WIDTH = 34;
    private static final int HEIGHT = 43;
    private static final int LIFE_POINT = 50;

    public Randy(int x, int y) {
        super(WIDTH, HEIGHT, LIFE_POINT);
        super.setPosition(x, y);
    }

    @Override
    public DefaultSpriteRenderer<? extends Sprite> getRenderedClass() {
        return new RandyRenderer();
    }
}
