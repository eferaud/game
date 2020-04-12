package fr.feraud.secretofnina;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application implements EventHandler<KeyEvent> {

    private Lapin lapin;

    @Override
    public void start(Stage stage) {

        ApplicationParameters applicationParameters = new ApplicationParameters();

        //Init contexte graphique
        Group root = new Group();
        Scene theScene = new Scene(root);
        stage.setScene(theScene);
        stage.setTitle("SecretOfNina " + applicationParameters.getWidth() + "X" + applicationParameters.getHeight());
        theScene.setFill(Color.BLACK);
        Canvas canvas = new Canvas(applicationParameters.getWidth(), applicationParameters.getHeight());
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Attachment du listener clavier
        root.setFocusTraversable(true);
        root.requestFocus();
        root.setOnKeyPressed(this);

        lapin = new Lapin(100, 180);

        stage.show();

        AnimationTimer animator = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                gc.clearRect(0, 0, applicationParameters.getWidth(), applicationParameters.getHeight());
                lapin.render(gc);
            }
        };

        animator.start();

    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void handle(KeyEvent keyEvent) {

        if (null != keyEvent.getCode()) {
            switch (keyEvent.getCode()) {
                case RIGHT:
                    lapin.setDirection(DirectionEnum.RIGHT);
                    break;
                case LEFT:
                    lapin.setDirection(DirectionEnum.LEFT);
                    break;
                case UP:
                    lapin.setDirection(DirectionEnum.UP);
                    break;
                case DOWN:
                    lapin.setDirection(DirectionEnum.DOWN);
                case ESCAPE:
                    lapin.attack();
                    break;
                default:
                    break;
            }
        }
    }

}
