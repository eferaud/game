/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model;

import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author eric
 */
public interface ICollisible {

    void update(double time);

    void rollback(double time);

    void setInCollision(boolean inCollision);

    void eraseVelocity();

    List<Point2D> getClipping();

}
