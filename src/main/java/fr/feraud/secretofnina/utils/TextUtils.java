/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.utils;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author eric
 */
public class TextUtils {

    public static void shadowText(GraphicsContext g, String text, double textX, double textY) {
        Font font = new Font("Arial Bold", 18);
        Text t = new Text(text);
        t.setFont(font);

        Canvas tmpCanvas = new Canvas(t.getLayoutBounds().getWidth(), t.getLayoutBounds().getHeight());
        // set configuration
        GraphicsContext tmpContext = tmpCanvas.getGraphicsContext2D();
        tmpContext.setFont(font);
        tmpContext.setFill(Color.RED);
        tmpContext.setEffect(new DropShadow(2, 0, 0, Color.BLACK));

        // draw on temporary context
        tmpContext.fillText(text, 0, font.getSize());

        // take a snapshot of the text
        final SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        WritableImage snapshot = tmpCanvas.snapshot(params, null);

        g.drawImage(snapshot, textX, textY - font.getSize());
    }

}
