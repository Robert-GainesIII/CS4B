/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj;

import bj.Deck.CARD;
import java.awt.Font;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author bobby
 */
public class Player {
    
    private String Name;
    
    private double money;
    
    private int cardCount;
    
    private Tab tab;
    
    private HBox hbox; 
    
    private HBox previewBox;
    
    ArrayList<Card> cards;
    
    private boolean myTurn;
    
    private Label nameLabel;
    
    private Label countLabel;
    
    public Player(String name, double money){
        Name = name;
        this.money = money;
        cards = new ArrayList();
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
        nameLabel.setText(Name);
    }

    public Label getCountLabel() {
        return countLabel;
    }

    public void setCountLabel(Label countLabel) {
        this.countLabel = countLabel;
        countLabel.setText("" + cardCount);
    }


    public HBox getPreviewBox() {
        return previewBox;
    }

    public void setPreviewBox(HBox previewBox) {
        this.previewBox = previewBox;
    }
    

    public double getMoney() {
        return money;
    }

    public void subtractMoney(double money) {
        this.money -= money;
    }

    public int getCardCount() {
        return cardCount;
    }
    
    public void addCard(Card c){
        c.setxBound(200);
        getHBox().getChildren().add(c.getPane());
        c.setxBound(getPreviewBox().getWidth()/6);
        c.setyBound(getPreviewBox().getHeight());
        getPreviewBox().getChildren().add(c.getPane());
        cards.add(c);
    }

    public void addCardCount(int card) {
        this.cardCount += card;
        if(cardCount > 21){
            for(Card c: cards){
                if(c.type == CARD.ACE && c.getValue() == 11){
                    c.setValue(1);
                    cardCount-=10;
                    break;
                }
            }
            myTurn = false;
            bust();
        }
    }
    
    public void bust(){
        System.out.println("ugh");
        countLabel.setTextFill(Color.RED);
        countLabel.setText("BUST!");
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab a) {
        tab = a;
    }
    
    public void setHBox(HBox h){
        hbox = h;
    }
    
    public HBox getHBox(){
        return hbox;
    }
    
    public void setTurn(boolean a){
        myTurn = a;
    }
    public boolean isUp(){
        return myTurn;
    }
    
    public String getName(){
        return Name;
    }
    
    
     public int updateUI(Player p, ArrayList<Player> players){
            if(p.getCardCount() > 21){
                //add if statement checking if player is the last in the array to avoid indexOutOfBounds Exception
               int a = players.indexOf(p);
               
              if(!(players.indexOf(p) == players.size()-1)){
                  players.get(a).setTurn(true);
                  return -1;
              }
              else{
                  return -2;
              }
               
            }
            else if(p.getCardCount() == 21){
               if(!(players.indexOf(p) == players.size()-1)){
               int a = players.indexOf(p)+1;
               players.get(a).setTurn(true);
               p.getCountLabel().setText("" + p.getCardCount());
               return -1;
               }
               else{
                   return -2;
               }
            }
            else  {
                p.getCountLabel().setText("" + p.getCardCount());
                return 0;
            }
        }
    
    
}
