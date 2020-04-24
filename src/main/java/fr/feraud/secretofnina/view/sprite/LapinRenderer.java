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
 *
 * @author eric
 */
public class LapinRenderer extends DefaultSpriteRenderer {

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

    public LapinRenderer() {
        for (MovementTypeEnum movementType : CONFIG.keySet()) {
            for (DirectionEnum direction : DirectionEnum.values()) {
                SpriteEvent spriteEvent = new SpriteEvent(direction, movementType);
                List<Image> images = getImages(spriteEvent);
                if (DIR2REVERSE.equals(direction)) {
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
        int offsetY = 0;
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
}
