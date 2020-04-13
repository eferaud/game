/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view;

import fr.feraud.secretofnina.DirectionEnum;
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
 *
 * @author eric
 * @param <T>
 */
public abstract class DefaultSpriteRenderer<T extends Sprite> {

    private final static int FRAME_RATE = 6;

    public void render(Sprite player, GraphicsContext graphicsContext) {

        int imageNumber;
        int nbrImage = getMAP().get(player.getDirection()).size();

        //@param dx the destination rectangle's X coordinate position.
        double dx = player.getPositionX();

        //@param dy the destination rectangle's Y coordinate position.
        double dy = player.getPositionY();

        //@param dw the destination rectangle's width.
        double dw = player.getWidth();

        int mod = player.getLoopCounter() % (FRAME_RATE * nbrImage);

        if (!player.isCurrentlyMoving()) {
            imageNumber = 1;
        } else {
            imageNumber = 1;
            /*if (mod < FRAME_RATE) {
                imageNumber = 2;
            } else if (mod < FRAME_RATE * 2) {
                imageNumber = 3;
            } else if (mod < FRAME_RATE * 3) {
                imageNumber = 4;
            } else if (mod < FRAME_RATE * 4) {
                imageNumber = 5;
            } else if (mod < FRAME_RATE * 5) {
                imageNumber = 6;
            } else if (mod < FRAME_RATE * 6) {
                imageNumber = 7;
            } else {
                imageNumber = 1;
            }*/

            int currentFrameRate = FRAME_RATE;
            for (int i = 1; i <= nbrImage; i++) {
                if (mod <= currentFrameRate) {
                    imageNumber = i;
                    break;
                } else {
                    currentFrameRate = FRAME_RATE * (i + 1);
                }
            }
            System.out.println("LoopCounter : " + player.getLoopCounter() + " /  imageNumber=" + imageNumber);
        }

        //@param dh the destination rectangle's height.
        double dh = player.getHeight();

        Image image = getMAP().get(player.getDirection()).get(imageNumber - 1);
        switch (player.getDirection()) {
            case RIGHT:
                break;
            case LEFT:
                dw = -dw;
                dx = dx - dw;
                break;
            case UP:
                break;
            case DOWN:
                break;
        }
        graphicsContext.drawImage(image, 0, 0, player.getWidth(), player.getHeight(), dx, dy, dw, dh);
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
