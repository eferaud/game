/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.sprite;

import fr.feraud.secretofnina.model.DirectionEnum;
import fr.feraud.secretofnina.model.MovementTypeEnum;
import fr.feraud.secretofnina.model.SpriteEvent;
import fr.feraud.secretofnina.utils.ImageUtils;
import fr.feraud.secretofnina.utils.Segment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;

/**
 * 31 x 27
 *
 * 1110 x 37 -> 186 x 27 (6 sprites) : walk left
 *
 * @author eric
 */
public class TotoRenderer extends DefaultSpriteRenderer {

    private final static String PATH = "file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\charset\\Elfe.png";
    private final static int WIDTH = 30;
    private final static int HEIGHT = 28;
    private final static DirectionEnum DIR2REVERSE = DirectionEnum.RIGHT;

    private final static Map<MovementTypeEnum, Segment> CONFIG = new HashMap<>();
    private final static Image tilesetImage = ImageUtils.buildImageWithTransparency(PATH);
    private final static Map<SpriteEvent, List<Image>> MAP = new HashMap<>();

    static {
        CONFIG.put(MovementTypeEnum.STOPED, new Segment(1, 1));
        CONFIG.put(MovementTypeEnum.WALK, new Segment(2, 6));
        CONFIG.put(MovementTypeEnum.ATTACK, new Segment(8, 3));
    }

    public TotoRenderer() {
        for (MovementTypeEnum movementType : CONFIG.keySet()) {
            for (DirectionEnum direction : DirectionEnum.values()) {
                SpriteEvent spriteEvent = new SpriteEvent(direction, movementType);
                List<Image> images = getImages(spriteEvent);
                if (direction.name().contains(DIR2REVERSE.name())) {
                    MAP.put(spriteEvent, reverse(images));
                } else {
                    MAP.put(spriteEvent, images);
                }
            }
        }
    }

    private List<Image> getImages(SpriteEvent spriteEvent) {
        Segment segment = CONFIG.get(spriteEvent.getMovementType());
        int offsetX = segment.getBegin() * WIDTH;
        int offsetY = getOffsetY(spriteEvent.getDirection()) * HEIGHT;
        int nbrTilesX = segment.getLength();
        return new ArrayList<>(ImageUtils.getSubTilesFromTileset(tilesetImage, offsetX, offsetY, WIDTH, HEIGHT, nbrTilesX, 1).values());
    }

    private static List<Image> reverse(List<Image> images) {
        List<Image> reserveImages = new ArrayList<>();

        images.forEach((image) -> {
            reserveImages.add(ImageUtils.flipImage(image));
        });

        return reserveImages;
    }

    @Override
    public Map<SpriteEvent, List<Image>> getMAP() {
        return MAP;
    }

    private int getOffsetY(DirectionEnum direction) {
        switch (direction) {
            case LEFT:
            case DOWN_LEFT:
            case UP_LEFT:
            case RIGHT:
            case UP_RIGHT:
            case DOWN_RIGHT:
                return 1;
            case DOWN:
                return 2;
            case UP:
                return 3;
        }
        return 0;
    }

}
