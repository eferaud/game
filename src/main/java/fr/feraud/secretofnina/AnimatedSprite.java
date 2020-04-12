/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author eric
 */
public abstract class AnimatedSprite extends Sprite {

    private DirectionEnum direction = DirectionEnum.DOWN;
    private StatusEnum status = StatusEnum.STOPED;

    //@param sx the source rectangle's X coordinate position.
    double spriteX = 0;//viewPort.getMinX();

    //@param sy the source rectangle's Y coordinate position.
    double spriteY = 115; //viewPort.getMinY();

    public AnimatedSprite(String spriteSheetPath, double width, double height) {
        super(spriteSheetPath, width, height);
    }

    @Override
    public void render(GraphicsContext gc) {
        Rectangle2D viewPort = super.getImageView().getViewport();

        //@param sx the source rectangle's X coordinate position.
        double sx = spriteX;//viewPort.getMinX();

        //@param sy the source rectangle's Y coordinate position.
        double sy = spriteY; //viewPort.getMinY();

        //@param sw the source rectangle's width.
        double sw = viewPort.getWidth();

        //@param sh the source rectangle's height.
        double sh = viewPort.getHeight();

        //@param dx the destination rectangle's X coordinate position.
        double dx = super.getPositionX();

        //@param dy the destination rectangle's Y coordinate position.
        double dy = super.getPositionY();

        //@param dw the destination rectangle's width.
        double dw = viewPort.getWidth();
        if (direction == DirectionEnum.LEFT) {
            dw = -dw;
            dx = dx - dw;
        }

        //@param dh the destination rectangle's height.*/
        double dh = viewPort.getHeight();
        //super.getImageView().setViewport(new Rectangle2D(sx, sy, viewPort.getWidth(), viewPort.getHeight()));
        gc.drawImage(super.getImageView().getImage(), sx, sy, sw, sh, dx, dy, dw, dh);

    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
        super.setPosition(super.getPositionX() + 10, super.getPositionY()); //TODO
    }

    public DirectionEnum getDirection() {
        return this.direction;
    }

    // protected abstract Rectangle2D getSpritePosition();
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
        switch (status) {
            case ATTACK:
                spriteY = 169;
                break;
            default:
                spriteY = 115;
                break;
        }

    }
}
