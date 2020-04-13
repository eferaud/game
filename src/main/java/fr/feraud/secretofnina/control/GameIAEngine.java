/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.control;

import fr.feraud.secretofnina.model.Lapin;
import fr.feraud.secretofnina.model.Sprite;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eric
 */
public class GameIAEngine {

    List<Sprite> players;

    private final static Map<Class, DefaultIA> MAP_IA = new HashMap<>();

    static {
        MAP_IA.put(Lapin.class, new RandomIA());
    }

    public GameIAEngine(List<Sprite> players) {
        this.players = players;
    }

    /**
     *
     * @param time Le temps en milliseconde depuis le dernier appel
     */
    public void play(double time) {
        players.forEach((player) -> {
            play(player, time);
        });
    }

    private void play(Sprite player, double time) {
        if (MAP_IA.containsKey(player.getClass())) {
            MAP_IA.get(player.getClass()).play(player);
        }
    }

}
