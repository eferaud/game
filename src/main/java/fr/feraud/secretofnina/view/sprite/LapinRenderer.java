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

    private final static String PATH = "file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\charset\\LapinSprites.png";
    private final static int WIDTH = 31;
    private final static int HEIGHT = 27;
    private final static DirectionEnum DIR2REVERSE = DirectionEnum.RIGHT;

    private final static Map<MovementTypeEnum, Segment> CONFIG = new HashMap<>();
    private final static Image tilesetImage = ImageUtils.buildImageWithTransparency(PATH);
    private final static Map<SpriteEvent, List<Image>> MAP = new HashMap<>();

    static {
        CONFIG.put(MovementTypeEnum.STOPED, new Segment(1, 1));
        CONFIG.put(MovementTypeEnum.WALK, new Segment(2, 2));
        CONFIG.put(MovementTypeEnum.RUN, new Segment(2, 2));
        CONFIG.put(MovementTypeEnum.ATTACK, new Segment(4, 4));
        CONFIG.put(MovementTypeEnum.HURT, new Segment(8, 2));
        CONFIG.put(MovementTypeEnum.DIE, new Segment(1, 10)); //@TODO refaire mieux
    }

    public LapinRenderer() {
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

    @Override
    public Map<SpriteEvent, List<Image>> getAnimationFromEvent() {
        return MAP;
    }
}
