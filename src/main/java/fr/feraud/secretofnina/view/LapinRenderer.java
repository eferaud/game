/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view;

import fr.feraud.secretofnina.model.DirectionEnum;
import static fr.feraud.secretofnina.model.DirectionEnum.DOWN;
import static fr.feraud.secretofnina.model.DirectionEnum.LEFT;
import static fr.feraud.secretofnina.model.DirectionEnum.RIGHT;
import static fr.feraud.secretofnina.model.DirectionEnum.UP;
import fr.feraud.secretofnina.model.Lapin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;

/**
 *
 * @author eric
 */
public class LapinRenderer extends DefaultSpriteRenderer<Lapin> {

    private final static Image I1 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\lapin1.png");
    private final static Image I2 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\lapin2.png");
    private final static Image I3 = buildImageWithTransparency("file:D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\lapin3.png");

    private final static Map<DirectionEnum, List<Image>> MAP = new HashMap<>();

    static {
        List<Image> animList = new ArrayList<>();
        animList.add(I1);
        animList.add(I2);
        animList.add(I3);
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
