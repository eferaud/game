/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static Image flipImage(Image image) {

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

                writer.setColor((int) image.getWidth() - x - 1, y, color);
            }
        }
        return wImage;
    }

    //TODO : mettre un système de cache mémoire (id image -> List<Point2D> détourrés)
    //Comme ça il n'y a plus à faire la translation reserve ou pas
    public static List<Point2D> getClipping(Image image, double tx, double ty, boolean reverse) {

        List<Point2D> points = new ArrayList<>();
        PixelReader pixelReader = image.getPixelReader();

        //Balayage total, pas optimal
        for (int y = 0; y < (int) image.getHeight(); y++) {
            for (int x = 0; x < (int) image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                if (color != null) {
                    //c'est un contour
                    if (reverse) {
                        points.add(new Point2D((int) (image.getWidth() - x + tx), (int) (y + ty)));
                    } else {
                        points.add(new Point2D((int) (x + tx), (int) (y + ty)));
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

    public static Map<Point2D, Image> getTilesFromTileset(String tileset, int tileWidth, int tileHeight) {
        Map<Point2D, Image> tiles = new HashMap<>();
        Image tilesetImage = new Image(tileset);

        if (tilesetImage.getWidth() % tileWidth > 0) {
            throw new RuntimeException("L'image tileset n'a pas une largeur multiple de " + tileWidth);
        }
        if (tilesetImage.getHeight() % tileHeight > 0) {
            throw new RuntimeException("L'image tileset n'a pas une hauteur multiple de " + tileHeight);
        }

        for (int y = 0; y < (tilesetImage.getHeight() / tileHeight); y++) {
            for (int x = 0; x < (tilesetImage.getWidth() / tileWidth); x++) {
                tiles.put(new Point2D(x, y), getTileFromTileset(tilesetImage, tileWidth, tileHeight, x, y, 0, 0));
            }
        }

        return tiles;
    }

    /**
     *
     * @param tilesetImage L'image du tileset
     * @param offsetX Point x de démarrage de la sous image
     * @param offsetY Point y de démarrage de la sous image
     * @param tileWidth Largeur du tile
     * @param tileHeight Hauteur du tile
     * @param nbrTilesX Nbr de tile sur l'axe X
     * @param nbrTilesY Nbr de tile sur l'axe y
     * @return Map de point relatif à la sous image ie premier point 0x0
     */
    public static Map<Point2D, Image> getSubTilesFromTileset(Image tilesetImage, int offsetX, int offsetY, int tileWidth, int tileHeight, int nbrTilesX, int nbrTilesY) {
        Map<Point2D, Image> tiles = new HashMap<>();

        for (int y = 0; y < nbrTilesY; y++) {
            for (int x = 0; x < nbrTilesX; x++) {
                tiles.put(new Point2D(x, y), getTileFromTileset(tilesetImage, tileWidth, tileHeight, x, y, offsetX, offsetY));
            }
        }
        return tiles;
    }

    /**
     * @param tilesetImage L'image du tileset
     * @param tileWidth la largeur du tile
     * @param tileHeight La hauteur du tile
     * @param tileX La position X du tile à récupérer dans le tileset (pas en
     * pixel, commence à 0)
     * @param tileY La position Y du tile à récupérer dans le tileset (pas en
     * pixel, commence à 0)
     * @param offsetX Position X du pixel de départ
     * @param offsetY Position Y du pixel de départ
     * @return
     */
    public static Image getTileFromTileset(Image tilesetImage, int tileWidth, int tileHeight, int tileX, int tileY, int offsetX, int offsetY) {

        PixelReader pixelReader = tilesetImage.getPixelReader();
        WritableImage tileImage = new WritableImage(tileWidth, tileHeight);
        PixelWriter writer = tileImage.getPixelWriter();

        for (int y = 0; y < tileHeight; y++) {
            for (int x = 0; x < tileWidth; x++) {
                //Retrieving the color of the pixel of the loaded image
                Color color = pixelReader.getColor(tileX * tileWidth + x + offsetX, tileY * tileHeight + y + offsetY);
                writer.setColor(x, y, color);
            }
        }
        return tileImage;
    }
}
