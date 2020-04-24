/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.sprite;

import fr.feraud.secretofnina.model.GameCamera;
import fr.feraud.secretofnina.model.MovementTypeEnum;
import fr.feraud.secretofnina.model.Sprite;
import fr.feraud.secretofnina.model.SpriteEvent;
import fr.feraud.secretofnina.model.SpriteStatusEnum;
import fr.feraud.secretofnina.utils.ImageUtils;
import fr.feraud.secretofnina.view.IRenderer;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Les Renderer sont stateless
 *
 * @author eric
 */
public abstract class DefaultSpriteRenderer implements IRenderer<Sprite, Canvas> {

    private final static Logger LOG = Logger.getLogger(DefaultSpriteRenderer.class.getName());

    private final static int FRAME_RATE = 6;

    @Override
    public Canvas initLayer(Sprite element, GameCamera gameCamera, Pane parent, int width, int height) {
        Canvas spriteLayer = new Canvas(width, height);
        parent.getChildren().add(spriteLayer);
        return spriteLayer;
    }

    @Override
    public void render(Sprite player, Canvas layer) {
        int nbrImage = getMAP().get(player.getSpriteEvent()).size();

        //Si changement de mouvement ou arret de tout mouvement, on repasse au loop compteur 1
        if (player.isEventChanged() || player.getSpriteEvent().getMovementType().equals(MovementTypeEnum.STOPED)) {
            player.setLoopCounter(1);
            player.setEventChanged(false);
        } else {
            if (player.getLoopCounter() == (FRAME_RATE * nbrImage)) {
                player.setLoopCounter(1);
            } else if ((player.getLoopCounter() < FRAME_RATE) && player.isCurrentlyMoving()) { //éviter l'effet de glissement
                player.setLoopCounter(FRAME_RATE);
            }
        }

        int imageNumber = player.getLoopCounter() / FRAME_RATE;

        System.out.println(player.getSpriteEvent() + "  ====> IMG " + imageNumber + " changed:" + player.isEventChanged());
        Image image = getMAP().get(player.getSpriteEvent()).get(imageNumber); //Attack sur nbr 3

        //Si le player était en action d'animation et qu'on arrive à la fin
        //On le rebascule en arret et on stoppe le statut acting
        if (player.getStatus().isAnimated() && imageNumber == (nbrImage - 1)) {
            player.setStatus(SpriteStatusEnum.STAND);
            player.move(player.getSpriteEvent().getDirection(), MovementTypeEnum.STOPED);
            player.setLoopCounter(1);
        }

        //@param dx the destination rectangle's X coordinate position.
        double dx = player.getMapPositionX();

        //@param dy the destination rectangle's Y coordinate position.
        double dy = player.getMapPositionY();

        //@param dw the destination rectangle's width.
        double dw = player.getWidth();

        //@param dh the destination rectangle's height.
        double dh = player.getHeight();

        layer.getGraphicsContext2D().drawImage(image, 0, 0, player.getWidth(), player.getHeight(), dx, dy, dw, dh);

        if (player.isCurrentlyMoving()) {
            player.setLoopCounter(player.getLoopCounter() + 1);
        } else {
            player.setLoopCounter(0);
        }

        player.setClipping(ImageUtils.getClipping(image, player.getMapPositionX(), player.getMapPositionY(), false));
    }

    public abstract Map<SpriteEvent, List<Image>> getMAP();

}
