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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author eric
 */
public class MapEditorController implements Initializable {

    private final static Logger LOG = Logger.getLogger(MapEditorController.class.getName());

    private final static int TILE_SIZE = 32;

    @FXML //  fx:id="tilesetCombo"
    private ComboBox<String> tilesetCombo; // Value injected by FXMLLoader

    @FXML
    private GridPane tilesetPane;

    @FXML
    private GridPane tilesPane;

    @FXML
    private MenuBar menuBar;

    private Image selectedTile;

    private Point2D selectedTilePosition;

    private StageMapJson datas;

    private Map<Point2D, Image> mapTiles;

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

    private void initMenuBar() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'editor.fxml'.";

    }

    private void initCombo() {
        assert tilesetCombo != null : "fx:id=\"tilesetCombo\" was not injected: check your FXML file 'editor.fxml'.";
        assert tilesetPane != null : "fx:id=\"tilesetCombo\" was not injected: check your FXML file 'editor.fxml'.";
        List<String> list = new ArrayList<>();
        list.add("tileset1.png");
        list.add("tileset4.png");
        ObservableList obList = FXCollections.observableList(list);

        tilesetCombo.setItems(obList);
        selectedTile = null;
        selectedTilePosition = null;
        tilesetPane.getChildren().clear();

        //selectedTileset.textProperty().bind(tilesetCombo.getSelectionModel().selectedItemProperty());
        // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
        tilesetCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldTileset, String newTileset) {
                if (newTileset != null) {
                    LOG.info("chargement " + newTileset);
                    datas.setTileSet(newTileset);

                    mapTiles = ImageUtils.getTilesFromTileset(StageMap.IMG_ROOT_PATH + newTileset, 16, 16);
                    for (Point2D point2D : mapTiles.keySet()) {
                        Rectangle rec = new Rectangle(TILE_SIZE, TILE_SIZE);
                        rec.setFill(new ImagePattern(mapTiles.get(point2D)));
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
                TileJson tileJson = datas.getTile(x, y);
                if (tileJson != null) {
                    rec.setFill(new ImagePattern(mapTiles.get(new Point2D(tileJson.getX(), tileJson.getY()))));
                } else {
                    rec.setFill(Color.WHITE);
                }

                rec.setStroke(Color.BLACK);
                tilesPane.add(rec, x, y);

                rec.setOnMouseClicked(e -> {
                    int cx = getCx(e);
                    int cy = getCy(e);
                    if (MouseButton.PRIMARY.equals(e.getButton())) {
                        fillCase(cx, cy, rec);
                    } else if (MouseButton.SECONDARY.equals(e.getButton())) {
                        eraseCase(cx, cy, rec);
                    }
                });
                rec.setOnMouseDragged(e -> {
                    LOG.info(e.getButton().name());

                    int cx = getCx(e);
                    int cy = getCy(e);
                    Rectangle rct2 = getNodeByRowColumnIndex(cy, cx, tilesPane);
                    if (MouseButton.PRIMARY.equals(e.getButton())) {
                        fillCase(cx, cy, rct2);
                    } else if (MouseButton.SECONDARY.equals(e.getButton())) {
                        eraseCase(cx, cy, rct2);
                    }
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
            datas = StageMapSerializer.deserialize("test.txt");
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
        TileJson tileJson = new TileJson(cx, cy, (int) selectedTilePosition.getX(), (int) selectedTilePosition.getY(), Boolean.TRUE, Boolean.FALSE);
        datas.saveOrUpdateTile(tileJson);
    }

    private void eraseCase(int cx, int cy, Rectangle rec) {
        LOG.log(Level.INFO, "clear case {0}.{1}", new Object[]{cx, cy});
        rec.setFill(Color.WHITE);
        TileJson tileJson = new TileJson(cx, cy, 0, 0, Boolean.TRUE, Boolean.FALSE);
        datas.removeTile(tileJson);
    }

}
