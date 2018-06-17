package it.polimi.ingsw.LM26.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


import java.io.IOException;

public class ControllerCardRec {

    @FXML
    private Label label;
    @FXML
    private Rectangle rec;

    public void setLable(String s){
        label.setText(s);
    }

    public String getLabel(){
        return label.getText();
    }
}
