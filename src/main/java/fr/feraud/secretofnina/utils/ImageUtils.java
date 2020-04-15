/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.utils;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author eric
 */
public class ImageUtils {

    //TODO : mettre un système de cache mémoire (id image -> List<Point2D> détourrés)
    //Comme ça il n'y a plus à faire la translation reserve ou pas
    public static List<Point2D> getClipping(Image image, int tx, int ty, boolean reverse) {

        List<Point2D> points = new ArrayList<>();
        PixelReader pixelReader = image.getPixelReader();

        //Balayage total, pas optimal
        for (int y = 0; y < (int) image.getHeight(); y++) {
            for (int x = 0; x < (int) image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                if (color != null) {
                    //c'est un contour
                    if (reverse) {
                        points.add(new Point2D(image.getWidth() - x + tx, y + ty));
                    } else {
                        points.add(new Point2D(x + tx, y + ty));
                    }
                }
            }
        }
        return points;
    }

    public static Image buildImageWithTransparency(String spriteSheetPath) {
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

}
