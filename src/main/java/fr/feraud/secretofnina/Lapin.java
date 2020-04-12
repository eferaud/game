/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Rectangle2D;

/**
 *
 * @author eric
 */
public class Lapin extends AnimatedSprite {

    private final static String SPRITE_SHEET_PATH = "file:C:\\Users\\eric\\Documents\\DEV\\SecretOfNina\\src\\resources\\LapinSprites.png";
    private static final int WIDTH = 33;
    private static final int HEIGHT = 27;

    private static Map<StatusEnum, List<Rectangle2D>> MAP_ANIMATED_FRAME;

    static {
        MAP_ANIMATED_FRAME = new EnumMap<>(StatusEnum.class);
        //Animation STOPED
        List<Rectangle2D> stop = new ArrayList<>();
        stop.add(new Rectangle2D(WIDTH, WIDTH, WIDTH, WIDTH))


        STOPED,
    WALK,
    RUN,
    ATTACK;

    }

    public Lapin(double x, double y) {
        super(SPRITE_SHEET_PATH, WIDTH, HEIGHT);
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
    void attack() {
        setStatus(StatusEnum.ATTACK);
    }
}
