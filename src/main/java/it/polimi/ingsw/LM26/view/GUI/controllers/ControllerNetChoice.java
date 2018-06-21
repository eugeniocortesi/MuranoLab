package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientBase;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ControllerNetChoice {

    @FXML
    private Button ok;
    @FXML
    private RadioButton socket;
    @FXML
    private RadioButton rmi;
    @FXML
    private ToggleGroup connection;

    ClientView clientView;
    ClientBase clientBase;



    //it returns the label on the radio button
    public void handleRmiSocketButton(ActionEvent event){
        RadioButton selected= (RadioButton)connection.getSelectedToggle();
        System.out.println(selected.getText());
        ok.setDisable(true);
        if(selected.getText().equals("RMI")){

        }
        else{

        }
    }
}
