package it.polimi.ingsw.LM26.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ControllerNetChoice {

    @FXML
    private RadioButton socket;
    @FXML
    private RadioButton rmi;
    @FXML
    private ToggleGroup connection;

    //it returns the label on the radio button
    public String handleRmiSocketButton(ActionEvent event){
        RadioButton selected= (RadioButton)connection.getSelectedToggle();
        System.out.println(selected.getText());
        return selected.getText();
    }
}