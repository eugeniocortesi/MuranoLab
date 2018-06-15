package it.polimi.ingsw.LM26.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ControllerLogin implements ViewControllerInt{

     @FXML
     Button bLogin;
     @FXML
     private TextField textField;
     @FXML
     private Label label;
     @FXML
     private Label label2;

     public String handleClick(ActionEvent actionEvent){
          String s=textField.getText();
          System.out.println(s);
          return s;
     }

     public void loggedScreen(){
          label.setText("Utente iscritto con successo");
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
