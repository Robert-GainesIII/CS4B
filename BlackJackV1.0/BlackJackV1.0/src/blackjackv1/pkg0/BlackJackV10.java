/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackv1.pkg0;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bobby
 */
public class BlackJackV10 extends Application {
    Stage primaryStage;
    Scene intro;
    Scene game;
    FXMLDocumentController xml =  new FXMLDocumentController();
    static Game global = new Game();
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        initScene();
        primaryStage.setScene(intro);
        primaryStage.show();
    }
    private void initScene() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Parent in = FXMLLoader.load(getClass().getResource("intro.fxml"));
        intro = new Scene(in);
        game = new Scene(root);
        global.setStage(primaryStage);
        global.setGameScene(game);
        global.setGameController(xml);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
    
}
