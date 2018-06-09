package it.polimi.ingsw.LM26.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;

public class MyRectangleController {

    @FXML private Label recLabel;

    private Pane pane;

    public void setLable(String s){
        recLabel.setText(s);
    }
}
