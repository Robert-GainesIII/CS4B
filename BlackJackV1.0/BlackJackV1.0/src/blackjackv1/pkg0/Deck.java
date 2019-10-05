/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackv1.pkg0;

import java.awt.Graphics;
import javafx.scene.image.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;

/**
 *
 * @author bobby
 */
public class Deck {
    
    Random rand = new Random();
    ArrayList<Card> deck;
    public Deck(){
         deck = init();
    }
    
     enum CARD{
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
    
    public ArrayList<Card> getDeck(){
        return deck;
    }
    
    public Card draw(double width, double  height){
        int r = rand.nextInt(deck.size());
        Card c = deck.get(r);
        c.setxBound(width);
        c.setyBound(height);
        return c;
    }
    
    public Image loadImage(CARD card){
        String url = "";
        Image image;
        switch(card){
            case ACE: 
            url = "Image/ace.png";
            break;
            case TWO: 
            url = "Image/two.png";
            break;
            case THREE:
            url = "Image/three.png";
            break;
            case FOUR: 
            url = "Image/four.png";
            break;
            case FIVE: 
            url = "Image/five.png";
            break;
            case SIX:
            url = "Image/six.png";
            break;
            case SEVEN:
            url = "Image/seven.png";
            break;
            case EIGHT:
            url = "Image/eight.png";
            break;
            case NINE:
            url = "Image/nine.png";
            break;
            case TEN:
            url = "Image/ten.png";
            break;
            case JACK: 
            url = "Image/jack.png";
            break;
            case QUEEN:
            url = "Image/queen.png";
            break;
            case KING:
            url = "Image/king.png";
            break;
            default:
            url = null;
        }
        try{
         return image = new Image(new FileInputStream(url));
        }catch(Exception e){e.printStackTrace();}
        
        return null;
    }
    
    public int getCardValue(CARD card){
        int value;
        switch(card){
            case ACE: value = 11;
            break;
            case TWO: value = 2;
            break;
            case THREE: value =3;
            break;
            case FOUR: value = 4;
            break;
            case FIVE: value = 5;
            break;
            case SIX: value = 6;
            break;
            case SEVEN: value = 7;
            break;
            case EIGHT: value = 8;
            break;
            case NINE: value = 9;
            break;
            case TEN: value = 10;
            break;
            case JACK: value = 10;
            break;
            case QUEEN: value = 10;
            break;
            case KING: value = 10;
            break;
            default:
             value = -1;
             System.out.println("Card Does not match any cards in deck.");
        }
       return value;
    }
    
    public ArrayList<Card> init(){
        CARD[] cardList = CARD.values();
        ArrayList<Card> cards = new ArrayList();
        for (CARD type : cardList) {
            if(!(loadImage(type) == null))
                cards.add(new Card(type, getCardValue(type), loadImage(type)));
            else{
                System.out.println("The image was null");
            }
        }
        return cards;
    }
}
