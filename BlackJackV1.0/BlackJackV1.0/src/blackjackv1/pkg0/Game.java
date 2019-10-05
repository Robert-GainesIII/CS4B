/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackv1.pkg0;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author bobby
 */
public class Game {
    
    ArrayList<Player> players;
    Stage PrimaryStage;
    static Scene Game;
    FXMLDocumentController control;
    
    public Game(){
        players = new ArrayList();
    }
    
    public void setStage(Stage s){
        PrimaryStage = s;
    }
    
    public void setScene(Scene s){
        PrimaryStage.setScene(s);
    }
    
    public void setGameScene(Scene s){
      Game = s;   
    }
    
    public FXMLDocumentController getController(){
        return control;
    }
    
    public void setGameController(FXMLDocumentController c){
        control = c;
    }
    public static Scene getGameScene(){
        return Game;
    }
    
    public ArrayList<Player> getPlayers(){
        return players;
    }
    
    public void addNewPlayer(String name){
        Player p = new Player(name, 1000);
        if(!players.isEmpty())p.setTurn(true);
        HBox playerBox =  new HBox();
        final Tab tab = new Tab(name);
        HBox hbox = new HBox();
        hbox.setPrefHeight(232.0);
        hbox.setPrefWidth(1000.0);
        tab.setContent(hbox);
        p.setTab(tab);
        p.setHBox(hbox);
        players.add(p);
        System.out.println(p.getName());
    }
}
