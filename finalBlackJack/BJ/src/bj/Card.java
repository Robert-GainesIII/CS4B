/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj;

import bj.Deck.CARD;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author bobby
 */
public class Card {
    
    public int value;
    public CARD type;
    public Image image;
    public double xBound;
    public double yBound;

    public double getxBound() {
        return xBound;
    }
    
    public void setValue(int a){
        value = a;
    }

    public void setxBound(double xBound) {
        this.xBound = xBound;
    }

    public double getyBound() {
        return yBound;
    }

    public void setyBound(double yBound) {
        this.yBound = yBound;
    }
    
    public Card(CARD type, int value, Image image){
        this.image = image;
        this.value = value;
        this.type = type;
    }
    
       public int getValue(){
        return value;
    }
    
    public CARD getCardType(){
        return type;
    }
    
    public Image getImage(){
        return image;
    }
    
    public Pane getPane(){
        ImageView iv1;
        iv1 = new ImageView(image);
        iv1.setFitHeight(yBound);
        iv1.setFitWidth(xBound);
        iv1.preserveRatioProperty();
        return new Pane(iv1);
    }
}

