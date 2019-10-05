/*
Note: This looked a lot prettier in my old fxml version, but I had trouble
tying the server to the client and redid the entire project.
 */
package client;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public class ClientApplication extends Application {
	private ArrayList<Thread> threads;
	public static void main(String[] args){
		launch();
	}
	
	@Override
	public void stop() throws Exception { 
		super.stop();
		for (Thread thread: threads){
			thread.interrupt();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception { 
		threads = new ArrayList<Thread>();
		primaryStage.setTitle("JavaFX Chatbox");
		primaryStage.setScene(makeInitScene(primaryStage));
		primaryStage.show();
	}

	public Scene makeInitScene(Stage primaryStage) { 
		GridPane rootPane = new GridPane();
		rootPane.setPadding(new Insets(20));
		rootPane.setVgap(10);
		rootPane.setHgap(10);
		rootPane.setAlignment(Pos.CENTER);
 
                
                
		TextField nameField = new TextField("TestyNamey");
		TextField hostNameField = new TextField("localhost");
		TextField portNumberField = new TextField("4242");
 
		Label nameLabel = new Label("Name");
		Label hostNameLabel = new Label("Host Name");
		Label portNumberLabel = new Label("Port Number");
		Label errorLabel = new Label(); 
		Button submitClientInfoButton = new Button("Done");
		submitClientInfoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent Event) { 
				Client client;
				try {
					client = new Client(hostNameField.getText(), Integer
							.parseInt(portNumberField.getText()), nameField
							.getText());
					Thread clientThread = new Thread(client);
					clientThread.setDaemon(true);
					clientThread.start();
					threads.add(clientThread);
					 
					primaryStage.close();
					primaryStage.setScene(makeChatUI(client));
					primaryStage.show();
				}
				catch(ConnectException e){
					errorLabel.setTextFill(Color.RED);
					errorLabel.setText("Invalid host name, try again");
				}
				catch (NumberFormatException | IOException e) { 
					errorLabel.setTextFill(Color.RED);
					errorLabel.setText("Invalid port number, try again");
				}
				
			}
		});
 
		rootPane.add(nameField, 0, 0);
		rootPane.add(nameLabel, 1, 0);
		rootPane.add(hostNameField, 0, 1);
		rootPane.add(hostNameLabel, 1, 1);
		rootPane.add(portNumberField, 0, 2);
		rootPane.add(portNumberLabel, 1, 2);
		rootPane.add(submitClientInfoButton, 0, 3, 2, 1);
		rootPane.add(errorLabel, 0, 4); 
		return new Scene(rootPane, 400, 400);
	}

	public Scene makeChatUI(Client client) { 
		GridPane rootPane = new GridPane();

                ColumnConstraints col1Constraints = new ColumnConstraints();
                col1Constraints.setPercentWidth(100);
                // Needed to find a way to align nodes across columns 
                
                //rootPane.getColumnConstraints().add(new ColumnConstraints(100));
                rootPane.getColumnConstraints().add(col1Constraints);
                
		rootPane.setPadding(new Insets(15)); 
		rootPane.setHgap(10);
		rootPane.setVgap(10);
 
		ListView<String> chatListView = new ListView<String>();
		chatListView.setItems(client.chatLog);
 
                TextArea chatView = new TextArea();
                chatView.setMaxWidth(600); 
                
		TextField chatTextField = new TextField("Type things here, and hit enter!");
		chatTextField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
				client.writeToServer(chatTextField.getText());
                                chatView.appendText(chatTextField.getText() + "\n");
				chatTextField.clear();
			}
		});
                 
                Button emoji1 = new Button(" :) ");
                Button emoji2 = new Button(" :D ");
                Button emoji3 = new Button(" :O ");
                Button emoji4 = new Button(" :-( ");
                Button emoji5 = new Button(" ᕙ(⇀‸↼‶)ᕗ ");
                Button emoji6 = new Button(" (◕‿◕✿) ");
                Button emoji7 = new Button(" (  ͡° ͜ʖ ͡° ) ");
                Button emoji8 = new Button(" (ノಠ益ಠ)ノ彡┻━┻ ");
                
                emoji1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
                        chatTextField.appendText(" :) ");
			}
		});
                emoji2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
                        chatTextField.appendText(" :D ");
			}
		});
                emoji3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
                        chatTextField.appendText(" :O ");
			}
		});
                emoji4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
                        chatTextField.appendText(" :-( ");
			}
		});
                emoji5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
                        chatTextField.appendText(" ᕙ(⇀‸↼‶)ᕗ ");
			}
		});
                emoji6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
                        chatTextField.appendText(" (◕‿◕✿) ");
			}
		});
                emoji7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
                        chatTextField.appendText(" (  ͡° ͜ʖ ͡° ) ");
			}
		});
                emoji8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
                        chatTextField.appendText("  (ノಠ益ಠ)ノ彡┻━┻  ");
			}
		});
                
                
                rootPane.add(emoji1, 0, 2);
                rootPane.add(emoji2, 0, 3);
                rootPane.add(emoji3, 0, 4);
                rootPane.add(emoji4, 0, 5);
                rootPane.add(emoji5, 0, 6);
                rootPane.add(emoji6, 0, 7);
                rootPane.add(emoji7, 0, 8);
                rootPane.add(emoji8, 0, 9);
		rootPane.add(chatListView, 0, 0);
		rootPane.add(chatTextField, 0, 1);
 
		return new Scene(rootPane, 600, 700);

	}
}
