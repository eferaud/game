/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view;

import fr.feraud.secretofnina.model.DirectionEnum;
import fr.feraud.secretofnina.model.Sprite;
import java.util.List;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Les Renderer sont stateless
 *
 * @author eric
 * @param <T>
 */
public abstract class DefaultSpriteRenderer<T extends Sprite> {

    private final static int FRAME_RATE = 6;

    public void render(Sprite player, GraphicsContext graphicsContext) {
        int nbrImage = getMAP().get(player.getDirection()).size();
        if (player.getLoopCounter() == (FRAME_RATE * nbrImage)) {
            player.setLoopCounter(1);
        } else if ((player.getLoopCounter() < FRAME_RATE) && player.isCurrentlyMoving()) { //éviter l'effet de glissement
            player.setLoopCounter(FRAME_RATE);
        }

        int imageNumber = player.getLoopCounter() / FRAME_RATE;

        Image image = getMAP().get(player.getDirection()).get(imageNumber);

        //@param dx the destination rectangle's X coordinate position.
        double dx = player.getPositionX();

        //@param dy the destination rectangle's Y coordinate position.
        double dy = player.getPositionY();

        //@param dw the destination rectangle's width.
        double dw = player.getWidth();

        //@param dh the destination rectangle's height.
        double dh = player.getHeight();

        switch (player.getDirection()) {
            case LEFT:
                dw = -dw;
                dx = dx - dw;
                break;
        }
        graphicsContext.drawImage(image, 0, 0, player.getWidth(), player.getHeight(), dx, dy, dw, dh);

        if (player.isCurrentlyMoving()) {
            player.setLoopCounter(player.getLoopCounter() + 1);
        } else {
            player.setLoopCounter(0);
        }
    }

    protected static Image buildImageWithTransparency(String spriteSheetPath) {
        Image spriteSheet = new Image(spriteSheetPath);
        Color backGroundColor = null;
        //Creating a writable image
        WritableImage wImage = new WritableImage((int) spriteSheet.getWidth(), (int) spriteSheet.getHeight());

        //Reading color from the loaded image
        PixelReader pixelReader = spriteSheet.getPixelReader();

        //getting the pixel writer
        PixelWriter writer = wImage.getPixelWriter();

        //Reading the color of the image
        for (int y = 0; y < (int) spriteSheet.getHeight(); y++) {
            for (int x = 0; x < (int) spriteSheet.getWidth(); x++) {
                //Retrieving the color of the pixel of the loaded image
                Color color = pixelReader.getColor(x, y);

                if (backGroundColor == null) {
                    backGroundColor = color;
                }

                if (backGroundColor.equals(color)) {
                    //writer.setColor(x, y, color.darker());
                } else {
                    writer.setColor(x, y, color);
                }
            }
        }
        return wImage;
    }

    public abstract Map<DirectionEnum, List<Image>> getMAP();

}
