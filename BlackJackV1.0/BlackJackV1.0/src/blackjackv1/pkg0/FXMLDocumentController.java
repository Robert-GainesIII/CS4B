/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackv1.pkg0;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author bobby
 */
public class FXMLDocumentController implements Initializable {
    
    Deck deck = new Deck();
    ArrayList<Player> players;
    @FXML
    public HBox hbox1;
    @FXML
    public HBox playerbox1;
    @FXML
    private Slider slider;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label betLabel;
    @FXML
    private Button hitButton;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab player1;
    @FXML
    private Label cardCount;
    @FXML
    private TabPane playerDock;
  
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        for(Player p : players){
       
        if(p.isUp()){
            System.out.println(players.indexOf(p));
        Card c = deck.draw(p.getHBox().getWidth(),p.getHBox().getHeight());
        Pane card = (c.getPane());
        p.addCardCount(c.getValue());
        p.getHBox().getChildren().add(card);
        p.getHBox().setAlignment(Pos.CENTER);

        cardCount.setText("Card Count: " + p.getCardCount());
        if(p.getCardCount() > 21){
            if(players.size() > 1 && (players.indexOf(p) < players.size())){
                players.get(players.indexOf(p) + 1).setTurn(true);
                playerDock.getSelectionModel().selectNext();
            }
        }
        }
        }
        hbox1.setAlignment(Pos.CENTER);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        moneyLabel.setText(moneyLabel.getText());
    }    
   

    @FXML
    private void handleSliderAction(MouseEvent event) {
        int value =(int)slider.getValue();
        betLabel.setText("Bet$$-> " + value);
    }
    

    public void initUI(){
        for(Player p : BlackJackV10.global.getPlayers()){
            System.out.println("ADDED TAB FOR: " + p.getName());
           playerDock.getTabs().add(p.getTab());
        }
    }
   
    
    
}
