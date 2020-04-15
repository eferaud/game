/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.sprite;

import fr.feraud.secretofnina.model.DirectionEnum;
import static fr.feraud.secretofnina.model.DirectionEnum.DOWN;
import static fr.feraud.secretofnina.model.DirectionEnum.LEFT;
import static fr.feraud.secretofnina.model.DirectionEnum.RIGHT;
import static fr.feraud.secretofnina.model.DirectionEnum.UP;
import fr.feraud.secretofnina.model.Randy;
import fr.feraud.secretofnina.utils.ImageUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;

/**
 *
 * @author eric
 */
public class RandyRenderer extends DefaultSpriteRenderer<Randy> {

    private final static Image R1 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy1.png");
    private final static Image R2 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy2.png");
    private final static Image R3 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy3.png");
    private final static Image R4 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy4.png");
    private final static Image R5 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy5.png");
    private final static Image R6 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy6.png");
    private final static Image R7 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy7.png");

    private final static Image D1 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy_D0.png");
    private final static Image D2 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy_D1.png");
    private final static Image D3 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy_D2.png");
    private final static Image D4 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy_D3.png");
    private final static Image D5 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy_D4.png");
    private final static Image D6 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy_D5.png");
    private final static Image D7 = ImageUtils.buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy_D6.png");

    private final static Map<DirectionEnum, List<Image>> MAP = new HashMap<>();

    static {
        List<Image> animList = new ArrayList<>();
        animList.add(R1);
        animList.add(R2);
        animList.add(R3);
        animList.add(R4);
        animList.add(R5);
        animList.add(R6);
        animList.add(R7);
        MAP.put(RIGHT, animList);
        MAP.put(LEFT, animList);
        MAP.put(UP, animList);
        MAP.put(DirectionEnum.UP_LEFT, animList);
        MAP.put(DirectionEnum.UP_RIGHT, animList);
        MAP.put(DirectionEnum.DOWN_LEFT, animList);
        MAP.put(DirectionEnum.DOWN_RIGHT, animList);

        List<Image> animListDown = new ArrayList<>();
        animListDown.add(D1);
        animListDown.add(D2);
        animListDown.add(D3);
        animListDown.add(D4);
        animListDown.add(D5);
        animListDown.add(D6);
        animListDown.add(D7);
        MAP.put(DOWN, animListDown);
    }

    @Override
    public Map<DirectionEnum, List<Image>> getMAP() {
        return MAP;
    }
}
