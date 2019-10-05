/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackv1.pkg0;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

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
    
    
    private boolean myTurn;
    
    public Player(String name, double money){
        this.money = money;
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

    public void addCardCount(int card) {
        this.cardCount += card;
        if(cardCount > 21){
            myTurn = false;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("BUST");
            alert.show();
        }
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
    
    
}
