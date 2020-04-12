/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author eric
 */
public class Sprite {

    private final ImageView imageView;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    public Sprite(String spriteSheetPath, double width, double height) {
        this.imageView = buildImageWithTransparency(spriteSheetPath);
        this.imageView.setViewport(new Rectangle2D(this.positionX, this.positionY, width, height));
        this.positionX = 0;
        this.positionY = 0;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void setVelocity(double x, double y) {
        this.velocityX = x;
        this.velocityY = y;
    }

    public void addVelocity(double x, double y) {
        this.velocityX += x;
        this.velocityY += y;
    }

    public void update(double time) {
        this.positionX += this.velocityX * time;
        this.positionY += this.velocityY * time;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(this.imageView.getImage(), this.positionX, this.positionY);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.positionX, this.positionY, this.width, this.height);
    }

    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public double getPositionX() {
        return this.positionX;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    @Override
    public String toString() {
        return " Position: [" + this.positionX + "," + this.positionY + "]"
                + " Velocity: [" + this.velocityX + "," + this.velocityY + "]";
    }

    private ImageView buildImageWithTransparency(String spriteSheetPath) {
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
        return new ImageView(wImage);
    }
}
