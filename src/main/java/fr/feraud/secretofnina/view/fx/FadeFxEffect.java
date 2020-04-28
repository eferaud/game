/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.view.fx;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author eric
 */
public class FadeFxEffect implements IFxEffect {

    //@TODO : prendre en compte le fond trasparent
    @Override
    public Image applyEffect(Image image, int loopCounter) {
        //Creating a writable image
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());

        //Reading color from the loaded image
        PixelReader pixelReader = image.getPixelReader();

        //getting the pixel writer
        PixelWriter writer = wImage.getPixelWriter();

        //Reading the color of the image
        for (int y = 0; y < (int) image.getHeight(); y++) {
            for (int x = 0; x < (int) image.getWidth(); x++) {
                //Retrieving the color of the pixel of the loaded image
                Color color = pixelReader.getColor(x, y);
                Color ncolor = new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, 1 - loopCounter / 10));
                writer.setColor(x, y, ncolor);
            }
        }
        return wImage;
    }

}
