/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.model.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eric
 */
public class StageMapSerializer {

    private static String ROOT_MAP_PATH = "D:\\DEV\\workspace\\SecretOfNina\\src\\resources\\maps\\";

    public static void serialize(StageMapJson bean) throws FileNotFoundException, IOException {

        //String json = new Gson().toJson(bean);
        String json = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create().toJson(bean);

        /* try (PrintStream out = new PrintStream(new FileOutputStream(bean.getMapFilePath()))) {
            out.print(json);
        }*/
        Files.writeString(Path.of(ROOT_MAP_PATH + bean.getName()), json, StandardCharsets.UTF_8);
    }

    public static StageMapJson deserialize(String file) throws IOException {

        String content = Files.readString(Path.of(ROOT_MAP_PATH + file), StandardCharsets.UTF_8);

        StageMapJson bean = new Gson().fromJson(content, StageMapJson.class);

        return bean;
    }

    public static void test() {

        StageMapJson map = new StageMapJson();
        map.setBackGroundImage("background.png");
        List<SpriteJson> ennemis = new ArrayList<>();
        ennemis.add(new SpriteJson("Lapin", 200, 200));
        map.setEnnemis(ennemis);
        map.setHeight(2);
        map.setWidth(2);
        map.setName("map1.txt");
        map.setTileSet("tileset1.png");
        List<TileJson> tiles = new ArrayList<>();
        tiles.add(new TileJson(0, 0, 0, 0, true, false));
        tiles.add(new TileJson(16, 16, 13, 3, true, false));
        map.setTiles(tiles);

        try {
            serialize(map);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StageMapSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StageMapSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            StageMapJson bean2 = deserialize(map.getName());
            bean2.setName("map3.txt");
            serialize(bean2);
        } catch (IOException ex) {
            Logger.getLogger(StageMapSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
