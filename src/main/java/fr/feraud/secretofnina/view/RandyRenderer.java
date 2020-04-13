/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view;

import fr.feraud.secretofnina.DirectionEnum;
import static fr.feraud.secretofnina.DirectionEnum.DOWN;
import static fr.feraud.secretofnina.DirectionEnum.LEFT;
import static fr.feraud.secretofnina.DirectionEnum.RIGHT;
import static fr.feraud.secretofnina.DirectionEnum.UP;
import fr.feraud.secretofnina.model.Randy;
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

    private final static Image I1 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy1.png");
    private final static Image I2 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy2.png");
    private final static Image I3 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy3.png");
    private final static Image I4 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy4.png");
    private final static Image I5 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy5.png");
    private final static Image I6 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy6.png");
    private final static Image I7 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\Randy7.png");

    private final static Map<DirectionEnum, List<Image>> MAP = new HashMap<>();

    static {
        List<Image> animList = new ArrayList<>();
        animList.add(I1);
        animList.add(I2);
        animList.add(I3);
        animList.add(I4);
        animList.add(I5);
        animList.add(I6);
        animList.add(I7);
        MAP.put(RIGHT, animList);
        MAP.put(LEFT, animList);
        MAP.put(UP, animList);
        MAP.put(DOWN, animList);
    }

    @Override
    public Map<DirectionEnum, List<Image>> getMAP() {
        return MAP;
    }

}
