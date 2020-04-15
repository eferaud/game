/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.control;

/**
 *
 * @author eric
 */
public interface IGameCollisionEngine {

    /**
     *
     * @param time Le temps en milliseconde depuis le dernier appel
     */
    void checkCollision(double time);

}
