/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.feraud.secretofnina.mapeditor;

import fr.feraud.secretofnina.model.StageMap;
import fr.feraud.secretofnina.model.json.StageMapJson;
import fr.feraud.secretofnina.model.json.StageMapSerializer;
import fr.feraud.secretofnina.model.json.TileJson;
import fr.feraud.secretofnina.utils.ImageUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author eric
 */
public class MapEditorController implements Initializable {

    private final static Logger LOG = Logger.getLogger(MapEditorController.class.getName());

    private static int TILE_SIZE = 32;

    @FXML //  fx:id="tilesetCombo"
    private ComboBox<String> tilesetCombo; // Value injected by FXMLLoader

    @FXML
    private ComboBox<String> metaCombo;

    @FXML
    private GridPane tilesetPane;

    @FXML
    private GridPane tilesPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TextFlow textFlow;

    private Image selectedTile;

    private Point2D selectedTilePosition;

    private StageMapJson datas;

    private Map<Point2D, Image> mapTiles;
    private boolean lastTrasparency;
    private boolean lastPlain;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datas = new StageMapJson();
        datas.setHeight(100);
        datas.setWidth(100);
        datas.setName("new.txt");
        datas.setTiles(new ArrayList<>());

        initMenuBar();
        initCombo();
        initTilesPane();
    }

    @FXML
    public void handleMenuAction(ActionEvent event) {
        LOG.info(((MenuItem) event.getTarget()).getId());

        switch (((MenuItem) event.getTarget()).getId()) {
            case "save":
                save();
                break;
            case "open":
                open();
                break;
            case "new":
                initialize(null, null);
                break;
        }
    }

    @FXML
    public void handleKeyTyped(KeyEvent event) {
        LOG.info(event.getCharacter());

        switch (event.getCharacter()) {
            case "+":
                TILE_SIZE = TILE_SIZE * 2;
                //tilesetCombo.getSelectionModel().select(datas.getTileSet());
                initTilesPane();
                break;
            case "-":
                TILE_SIZE = TILE_SIZE / 2;
                //tilesetCombo.getSelectionModel().select(datas.getTileSet());
                initTilesPane();
                break;
        }

    }

    private void initMenuBar() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'editor.fxml'.";

    }

    private void initCombo() {
        assert tilesetCombo != null : "fx:id=\"tilesetCombo\" was not injected: check your FXML file 'editor.fxml'.";
        assert tilesetPane != null : "fx:id=\"tilesetCombo\" was not injected: check your FXML file 'editor.fxml'.";
        assert metaCombo != null : "fx:id=\"metaCombo\" was not injected: check your FXML file 'editor.fxml'.";

        List<String> metas = new ArrayList<>();
        metas.add("tile");
        metas.add("transparency");
        metas.add("collision");
        ObservableList ometas = FXCollections.observableList(metas);
        metaCombo.setItems(ometas);

        List<String> list = new ArrayList<>();
        list.add("tileset1.png");
        list.add("tileset4.png");
        ObservableList obList = FXCollections.observableList(list);
        tilesetCombo.setItems(obList);

        tilesetCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldTileset, String meta) {
                if (meta != null) {
                    LOG.info("meta " + meta);

                }
            }
        });

        selectedTile = null;
        selectedTilePosition = null;

        //selectedTileset.textProperty().bind(tilesetCombo.getSelectionModel().selectedItemProperty());
        // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
        tilesetCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldTileset, String newTileset) {
                if (newTileset != null) {
                    LOG.info("chargement " + newTileset);
                    datas.setTileSet(newTileset);
                    tilesetPane.getChildren().clear();
                    selectedTile = null;
                    selectedTilePosition = null;
                    metaCombo.getSelectionModel().select("tile");

                    mapTiles = ImageUtils.getTilesFromTileset(StageMap.IMG_ROOT_PATH + newTileset, 16, 16);
                    for (Point2D point2D : mapTiles.keySet()) {
                        Rectangle rec = new Rectangle(32, 32); //On utilise pas TILE_SIZE, car ça nique tout l'affichage
                        rec.setFill(new ImagePattern(mapTiles.get(point2D)));
                        rec.setStroke(Color.BLACK);
                        rec.setStrokeType(StrokeType.INSIDE);
                        tilesetPane.add(rec, (int) point2D.getX(), (int) point2D.getY());

                        rec.setOnMouseClicked(e -> {
                            LOG.log(Level.INFO, "Select Tile x:{0} y:{1}", new Object[]{(int) point2D.getX(), (int) point2D.getY()});
                            selectedTile = mapTiles.get(point2D);
                            selectedTilePosition = point2D;
                        });
                    }
                }
            }
        });

        tilesetCombo.getSelectionModel().select(datas.getTileSet());
    }

    private void initTilesPane() {
        assert tilesPane != null : "fx:id=\"tilessPane\" was not injected: check your FXML file 'editor.fxml'.";
        tilesPane.getChildren().clear();

        final int numCols = datas.getWidth();
        final int numRows = datas.getHeight();

        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                Rectangle rec = new Rectangle(TILE_SIZE, TILE_SIZE);
                rec.setStroke(Color.BLACK);
                rec.setStrokeType(StrokeType.INSIDE);
                TileJson tileJson = datas.getTile(x, y);
                if (tileJson != null) {
                    rec.setFill(new ImagePattern(mapTiles.get(new Point2D(tileJson.getX(), tileJson.getY()))));
                    if (tileJson.getP()) { //si plain, on encadre en rouge
                        rec.setStroke(Color.RED);
                        rec.setStrokeType(StrokeType.INSIDE);
                    }
                    if (tileJson.getT()) { //si transparent, on entour en pointillet
                        rec.setOpacity(0.5);
                    }
                } else {
                    rec.setFill(Color.WHITE);
                }

                tilesPane.add(rec, x, y);

                rec.setOnMouseClicked(e -> {
                    LOG.info("Click " + e.getButton().name());
                    int cx = getCx(e);
                    int cy = getCy(e);
                    if (null != e.getButton()) {
                        switch (e.getButton()) {
                            case PRIMARY:
                                fillCase(cx, cy, rec);
                                break;
                            case SECONDARY:
                                LOG.info(metaCombo.getSelectionModel().getSelectedItem());
                                if (metaCombo.getSelectionModel().getSelectedItem().equals("tile")) {
                                    eraseCase(cx, cy, rec);
                                } else if (metaCombo.getSelectionModel().getSelectedItem().equals("transparency")) {
                                    switchTransparency(cx, cy, rec);
                                } else if (metaCombo.getSelectionModel().getSelectedItem().equals("collision")) {
                                    switchPlain(cx, cy, rec);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                });
                rec.setOnMouseDragged(e -> {
                    LOG.info("Drag " + e.getButton().name());

                    int cx = getCx(e);
                    int cy = getCy(e);

                    Rectangle rct2 = getNodeByRowColumnIndex(cy, cx, tilesPane);
                    if (null != e.getButton()) {
                        switch (e.getButton()) {
                            case PRIMARY:
                                fillCase(cx, cy, rct2);
                                break;
                            case SECONDARY:
                                if (metaCombo.getSelectionModel().getSelectedItem().equals("tile")) {
                                    eraseCase(cx, cy, rct2);
                                } else if (metaCombo.getSelectionModel().getSelectedItem().equals("transparency")) {
                                    setTransparency(cx, cy, rct2, lastTrasparency);
                                } else if (metaCombo.getSelectionModel().getSelectedItem().equals("collision")) {
                                    setPlain(cx, cy, rct2, lastPlain);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                });

                rec.setOnMouseMoved(e -> {
                    int cx = getCx(e);
                    int cy = getCy(e);

                    textFlow.getChildren().clear();
                    textFlow.getChildren().add(new Text(cx + " x " + cy));
                    textFlow.getChildren().add(new Text("    //    " + e.getSceneX() + " x " + (e.getSceneY() - 23)));
                });

            }
        }

    }

    public static int getCx(MouseEvent e) {
        return (int) (e.getSceneX() / TILE_SIZE);
    }

    public static int getCy(MouseEvent e) {
        return (int) ((e.getSceneY() - 23) / TILE_SIZE);
    }

    public static Rectangle getNodeByRowColumnIndex(final int y, final int x, GridPane pane) {
        Node result = null;
        ObservableList<Node> childrens = pane.getChildren();

        for (Node node : childrens) {

            if (node != null && node instanceof Rectangle && GridPane.getRowIndex(node) == y && GridPane.getColumnIndex(node) == x) {
                result = node;
                break;
            }
        }

        return (Rectangle) result;
    }

    private void open() {
        LOG.info("open()");
        try {
            datas = StageMapSerializer.deserialize("open.txt");
        } catch (IOException ex) {
            Logger.getLogger(MapEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        initMenuBar();
        initCombo();
        initTilesPane();
    }

    private void save() {
        try {
            StageMapSerializer.serialize(datas);
        } catch (IOException ex) {
            Logger.getLogger(MapEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCase(int cx, int cy, Rectangle rec) {
        LOG.log(Level.INFO, "fill case {0}x{1}", new Object[]{cx, cy});
        rec.setFill(new ImagePattern(selectedTile));
        TileJson tileJson = new TileJson(cx, cy, (int) selectedTilePosition.getX(), (int) selectedTilePosition.getY(), Boolean.FALSE, Boolean.FALSE);
        datas.saveOrUpdateTile(tileJson);
    }

    private void eraseCase(int cx, int cy, Rectangle rec) {
        LOG.log(Level.INFO, "clear case {0}.{1}", new Object[]{cx, cy});
        rec.setFill(Color.WHITE);
        rec.setStroke(Color.GRAY);
        rec.setStrokeType(StrokeType.INSIDE);
        rec.setOpacity(1);
        TileJson tileJson = new TileJson(cx, cy, 0, 0, Boolean.TRUE, Boolean.FALSE);
        datas.removeTile(tileJson);
    }

    private void switchPlain(int cx, int cy, Rectangle rec) {
        TileJson tileJson = datas.getTile(cx, cy);
        if (tileJson != null) {
            lastPlain = !tileJson.getP();
            setPlain(cx, cy, rec, lastPlain);
        }
    }

    private void switchTransparency(int cx, int cy, Rectangle rec) {
        TileJson tileJson = datas.getTile(cx, cy);
        if (tileJson != null) {
            lastTrasparency = !tileJson.getT();
            setTransparency(cx, cy, rec, lastTrasparency);
        }
    }

    private void setPlain(int cx, int cy, Rectangle rec, boolean plain) {
        TileJson tileJson = datas.getTile(cx, cy);
        if (tileJson != null) {
            tileJson.setP(plain);
            rec.setStrokeType(StrokeType.INSIDE);
            if (tileJson.getP()) { //si plain, on encadre en rouge
                rec.setStroke(Color.RED);
            } else {
                rec.setStroke(Color.BLACK);
            }
        }
    }

    private void setTransparency(int cx, int cy, Rectangle rec, boolean transparency) {
        TileJson tileJson = datas.getTile(cx, cy);
        if (tileJson != null) {
            tileJson.setT(transparency);

            if (tileJson.getT()) { //si transparent, on applique transparence
                rec.setOpacity(0.5);
            } else {
                rec.setOpacity(1);
            }
        }
    }

}
