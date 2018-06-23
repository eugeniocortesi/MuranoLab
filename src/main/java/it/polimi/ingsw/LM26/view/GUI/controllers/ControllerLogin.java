package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientBase;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientInt;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.view.GUI.ViewControllerInt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ControllerLogin implements ViewControllerInt {

     private ClientInt cBase;
     private ClientView cView;

     @FXML
     Button bLogin;
     @FXML
     private TextField textField;
     @FXML
     private Label label;
     @FXML
     private Label label2;

     public void setUp(ClientInt cBase, ClientView cView){
          this.cBase=cBase;
          this.cView=cView;
     }

     public void handleClick(ActionEvent actionEvent){
          String s=textField.getText();
          cView.login(s);
          cBase.setUsername(s);

     }

     public void loggedScreen(){
          label.setText("Utente iscritto con successo");
          bLogin.setDisable(true);
     }

     public void alreadyLoggedScreen(){
          label.setText("È già  presente un giocatore col tuo");
          label2.setText("nome utente, scegline un altro");
     }

     public void tooManyUsersScreen(){
          label.setText("Partita già iniziata");
     }

     public void addedPlayer(String name){
          label.setText("Nuovo giocatore aggiunto: "+name);
     }
}
