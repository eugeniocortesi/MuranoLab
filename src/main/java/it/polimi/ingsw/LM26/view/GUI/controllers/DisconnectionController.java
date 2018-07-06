package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.view.GUI.DisplayableStage;
import it.polimi.ingsw.LM26.view.GUI.View;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DisconnectionController {

    private Stage primaryStage;
    private View view;
    private DisplayableStage disp;
    private Stage discStage;

    public void disconnectionSetUp(Stage primaryStage, Stage discStage, View view, DisplayableStage disp) {
        this.primaryStage=primaryStage;
        this.discStage=discStage;
        this.disp=disp;
        this.view=view;
    }

    public void esc(ActionEvent event){
        view.disconnect();
        view.showDisconnectScreen();
        primaryStage.close();
    }

    public void stay(ActionEvent event){
        discStage.close();
    }
}
