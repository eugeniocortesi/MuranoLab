package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientBase;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientInt;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.view.GUI.View;
import it.polimi.ingsw.LM26.view.GUI.ViewControllerInt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ControllerLogin implements ViewControllerInt {


     @FXML
     Button bLogin;
     @FXML
     private TextField textField;
     @FXML
     private Label label;
     @FXML
     private Label label2;


     public void handleClick(ActionEvent actionEvent){
          String s=textField.getText();
         View.getClientView().login(s);
          View.getClientBase().setUsername(s);

     }

     public void loggedScreen(){
          label.setText("Utente iscritto con successo");
          label2.setText("");
          bLogin.setDisable(true);
     }

     public void alreadyLoggedScreen(){
          label.setText("È già  presente un giocatore col tuo");
          label2.setText("nome utente, scegline un altro");
     }

     public void tooManyUsersScreen(){
          label.setText("Partita già iniziata");
          label2.setText("");
     }

     public void addedPlayer(String name){
          label.setText("Nuovo giocatore aggiunto: "+name);
          label2.setText("");
     }
}
