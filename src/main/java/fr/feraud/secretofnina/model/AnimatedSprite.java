/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

/**
 *
 * @author eric
 */
@Deprecated
public abstract class AnimatedSprite extends Sprite {

    private DirectionEnum direction = DirectionEnum.DOWN;
    private MovementTypeEnum movementType = MovementTypeEnum.STOPED;
    private int frameCounter = 0;

    //@param sx the source rectangle's X coordinate position.
    double spriteX = 0;//viewPort.getMinX();

    //@param sy the source rectangle's Y coordinate position.
    double spriteY = 115; //viewPort.getMinY();

    /* @Override
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

        //@param dh the destination rectangle's height.
        double dh = viewPort.getHeight();
        //super.getImageView().setViewport(new Rectangle2D(sx, sy, viewPort.getWidth(), viewPort.getHeight()));
        gc.drawImage(super.getImageView().getImage(), sx, sy, sw, sh, dx, dy, dw, dh);

    }*/
    public AnimatedSprite(int width, int height, int lifeIndex) {
        super(width, height, lifeIndex);
    }
}
