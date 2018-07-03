package it.polimi.ingsw.LM26.view.GUI.controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class DisconnectionController {

    private Stage discStage;



    public void disconnectionSetUp(Stage discStage) {
        this.discStage=discStage;
    }

    public void esc(ActionEvent event){

    }

    public void stay(ActionEvent event){
        discStage.close();
    }
}
