/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj;

import bj.Deck.CARD;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author bobby
 */
public class BJ extends Application {
    
    double tabPaneWidth;
    double tabPaneHeight;
    TabPane playerPane;
    
    Stage stage;
    Player isUP;
    GridPane startPane;
    GridPane gamePane;
    ArrayList<Player> players;
    ArrayList<Card> deck;
    Random generate = new Random();
    HBox deal;
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        try {
            loadIntro();
        } catch (IOException ex) { Logger.getLogger(BJ.class.getName()).log(Level.SEVERE, null, ex); }
        stage.show();
    }

    public void loadIntro()throws IOException{
        players = new ArrayList();
        FXMLLoader intro = new FXMLLoader(getClass().getResource("start.fxml"));
        IntroEvent controller = new IntroEvent();
        intro.setController(controller);
        startPane = (GridPane)intro.load();
        Scene s = new Scene(startPane);
        stage.setScene(s);
    }
    
    public void loadGame()throws IOException{
        
        FXMLLoader game = new FXMLLoader(getClass().getResource("game.fxml"));
        GameEvent controller = new GameEvent();
        game.setController(controller);
        gamePane = (GridPane)game.load();
        Scene s = new Scene(gamePane);
        stage.setScene(s);
        stage.setFullScreen(true);
         for(Player p : players){
            
            Card one = drawCard(p);
            Card two = drawCard(p);
            p.addCard(one);
            p.addCardCount(one.getValue());
            p.addCard(two);
            p.addCardCount(two.getValue());
            p.updateUI(p, players);
            
        }
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
   public class IntroEvent implements Initializable{
       @FXML
        GridPane pane;
       @FXML
        VBox nameBox;
       @FXML
        VBox loadBox;
       @FXML
        Button startButton;
       @FXML
       Button user1Load;
       @FXML
       Button user2Load;
       @FXML
       Button user3Load;
       @FXML
       TextField user1;
       @FXML
       TextField user2;
       @FXML
       TextField user3;
       @FXML
       ChoiceBox cardCount;
         
        
        @Override
        public void initialize(URL location, ResourceBundle resources) {
                cardCount.setItems(FXCollections.observableArrayList
                (
                        1, 2, 3
                )
                );
            
        }
        
       
        public void handleStart(ActionEvent e) throws IOException{
            for(Node a : nameBox.getChildren()){
                TextField text = (TextField)a;
                if(text.getText().length() >= 1){
                    addNewPlayer(text.getText());
                }
            }
            deck = new Deck((int)cardCount.getValue()).getDeck();
            loadGame();
        }
    }
   
   
   public class GameEvent implements Initializable{
        @FXML
        GridPane pane;
        @FXML
        GridPane innerGrid1;
        @FXML
        GridPane innerGrid2;
        @FXML
        GridPane innerGrid3;
        @FXML
        HBox dealerBox;
        @FXML
        TabPane playerPane;
        @FXML
        Label nameLabel;
        @FXML
        Label countLabel;
        @FXML
        Label nameLabel2;
        @FXML
        Label countLabel2;
        @FXML
        Label nameLabel3;
        @FXML
        Label countLabel3;
        @FXML
        Button hit1;
        @FXML
        Button stay1;
        @FXML
        Button hit2;
        @FXML
        Button stay2;
        @FXML
        Button hit3;
        @FXML
        Button stay3;
        @FXML
        HBox previewBox1;
        @FXML
        HBox previewBox2;
        @FXML
        HBox previewBox3;
        
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            deal = dealerBox;
            
            int i = 0;
            for(Player p : players){
                HBox box = p.getHBox();
                box.setPrefHeight(playerPane.getHeight());
                box.setPrefWidth(playerPane.getWidth());
                box.prefHeightProperty().bind(playerPane.heightProperty());
                box.prefWidthProperty().bind(playerPane.widthProperty());
                playerPane.getTabs().add(p.getTab());
                switch(i){
                    
                    //PLAYER 1
                    case 0:
                        p.setPreviewBox(previewBox1);
                        isUP = p;
                        p.setTurn(true);
                        p.setNameLabel(nameLabel);
                        p.setCountLabel(countLabel);
                        
                        //HIT BUTTON
                        hit1.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent e){
                                if(p.isUp() && p.getCardCount() < 21){
                                    Card card = drawCard(p);
                                    if(card.type == CARD.ACE && 21 - p.getCardCount() < 10){
                                        card.setValue(1);
                                    }
                                    p.addCardCount(card.getValue());
                                    p.addCard(card);
                                    int a = p.updateUI(p, players);
                                    if(a == -1){
                                        if(players.indexOf(p) == players.size()-1){
                                            dealerDraw();
                                        }else{
                                              playerPane.getSelectionModel().selectNext();
                                        }
                                    }
                                    else if(a == -2){
                                        dealerDraw();
                                    }
                                }
                            }
                        });
                        
                        //STAY BUTTON
                        stay1.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent e){
                                p.setTurn(false);
                                if(players.indexOf(p) == players.size()-1){
                                            dealerDraw();
                                }else{
                                    int a = players.indexOf(p)+1;
                                    players.get(a).setTurn(true);
                                    playerPane.getSelectionModel().selectNext();
                                }
                            }
                        });
                        break;
                        
                        
                    //PLAYER 2
                    case 1:
                        p.setPreviewBox(previewBox2);
                        p.setNameLabel(nameLabel2);
                        p.setCountLabel(countLabel2);
                        
                        //HIT BUTTON
                        hit2.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent e){
                                if(p.isUp() && p.getCardCount() < 21){
                                    Card card = drawCard(p);
                                    if(card.type == CARD.ACE && 21 - p.getCardCount() < 10){
                                        card.setValue(1);
                                    }
                                    p.addCardCount(card.getValue());
                                    p.addCard(card);
                                    int a = p.updateUI(p, players);
                                    if(a == -1){
                                        if(players.indexOf(p) == players.size()-1){
                                            dealerDraw();
                                        }else{
                                             playerPane.getSelectionModel().selectNext();
                                        }
                                    }
                                    else if(a == -2){
                                        dealerDraw();
                                    }
                                }
                            }
                        });
                        
                        //STAY BUTTON
                        stay2.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent e){
                               p.setTurn(false);
                                if(players.indexOf(p) == players.size()-1){
                                            dealerDraw();
                                }else{
                                    int a = players.indexOf(p)+1;
                                    players.get(a).setTurn(true);
                                    playerPane.getSelectionModel().selectNext();
                                }
                            }
                        });
                    break;
                    
                    
                    //PLAYER 3
                    case 2:
                        p.setPreviewBox(previewBox3);
                        p.setNameLabel(nameLabel3);
                        p.setCountLabel(countLabel3);
                        
                        //HIT BUTTON
                        hit3.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent e){
                                if(p.isUp() && p.getCardCount() < 21){
                                    Card card = drawCard(p);
                                    if(card.type == CARD.ACE && 21 - p.getCardCount() < 10){
                                        card.setValue(1);
                                    }
                                    p.addCardCount(card.getValue());
                                    p.addCard(card);
                                    int a = p.updateUI(p, players);
                                    if(a == -1){
                                        if(players.indexOf(p) == players.size()-1){
                                            dealerDraw();
                                    }else{
                                        playerPane.getSelectionModel().selectNext();
                                        }
                                    }
                                    else if(a == -2){
                                        dealerDraw();
                                    }
                                }
                            }
                        });
                        //SATY BUTTON
                        stay3.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent e){
                                p.setTurn(false);
                                if(players.indexOf(p) == players.size()-1){
                                            dealerDraw();
                                }else{
                                    int a = players.indexOf(p)+1;
                                    players.get(a).setTurn(true);
                                    playerPane.getSelectionModel().selectNext();
                                }
                            }
                        });
                    break;
                    
                    
                    
                    default:
                    
                }
                i++;
            }
            if(players.size() >= 2 && players.size() < 3){
                System.out.println("2 players");
                players.get(1).setNameLabel(nameLabel3);
                players.get(1).setCountLabel(countLabel3);
                players.get(1).setPreviewBox(previewBox3);
                hit3.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent e){
                                System.out.println("click");
                                if(players.get(1).isUp() && players.get(1).getCardCount() < 21){
                                    Card card = drawCard(players.get(1));
                                    if(card.type == CARD.ACE && 21 - players.get(1).getCardCount() < 10){
                                        card.setValue(1);
                                    }
                                    players.get(1).addCardCount(card.getValue());
                                    players.get(1).addCard(card);
                                    int a = players.get(1).updateUI(players.get(1), players);
                                    if(a == -1){
                                        if(players.indexOf(players.get(1)) == players.size()-1){
                                            dealerDraw();
                                        }else{
                                        playerPane.getSelectionModel().selectNext();
                                        }
                                    }
                                    else if(a == -2){
                                        dealerDraw();
                                    }
                                }
                            }
                        });
                stay3.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent e){
                                players.get(1).setTurn(false);
                                if(players.indexOf(players.get(1)) == players.size()-1){
                                            dealerDraw();
                                }else{
                                    int a = players.indexOf(players.get(1))+1;
                                    players.get(a).setTurn(true);
                                    playerPane.getSelectionModel().selectNext();
                                }
                             }
                        });
                pane.getChildren().remove(innerGrid2);
            }
            if(players.size() < 2 && players.size() >= 1){
                System.out.println("1 player");
                pane.getChildren().remove(innerGrid2);
                pane.getChildren().remove(innerGrid3);
            }
            
            //DEAL EACH PLAYER 2 CARDS 
        }  
        
        public TabPane getTabPane(){
            return playerPane;
        }
  
    }
   
   public Card drawCard(Player p){
       HBox box = p.getHBox();
       int random = generate.nextInt(deck.size());
       Card c = deck.get(random);
       c.setyBound(box.getHeight());
       return c;
   }
   

   public void addNewPlayer(String name){
        Player p = new Player(name, 1000);
        HBox playerBox =  new HBox();
        playerBox.setStyle("fx-background-color : black");
        //playerBox.setPrefWidth(0);
        final Tab tab = new Tab(name);
        HBox hbox = new HBox();
        tab.setContent(hbox);
        p.setTab(tab);
        p.setHBox(hbox);
        players.add(p);
        System.out.println(p.getName() + " added to the game.");
    }
   
   public void dealerDraw(){
       for(int i = 0; i < 3; i++){
           int random = generate.nextInt(deck.size());
            Card c = deck.get(random);
            c.setyBound(deal.getHeight());
            deal.getChildren().add(c.getPane());
           try {
               System.out.println("sleep");
               Thread.sleep(200);
           } catch (InterruptedException ex) {
               Logger.getLogger(BJ.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
    
}
