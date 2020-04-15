/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.control;

import fr.feraud.secretofnina.model.DirectionEnum;
import fr.feraud.secretofnina.model.MovementTypeEnum;
import fr.feraud.secretofnina.model.Sprite;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author eric
 */
public class RandomIA extends DefaultIA {

    private final Random rand = new Random();

    public RandomIA() {

    }

    @Override
    public void play(Sprite sprite) {
        int randDirection = rand.nextInt(8);
        int randAction = rand.nextInt(4);
        sprite.move(Arrays.asList(DirectionEnum.values()).get(randDirection), Arrays.asList(MovementTypeEnum.values()).get(randAction));
    }

}
