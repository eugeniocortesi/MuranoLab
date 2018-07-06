package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.view.GUI.DisplayableStage;
import it.polimi.ingsw.LM26.view.GUI.View;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DisconnectionController {

    private Stage discStage;
    private View view;
    private DisplayableStage disp;

    public void disconnectionSetUp(Stage discStage, DisplayableStage disp, View view) {
        this.discStage=discStage;
        this.disp=disp;
        this.view=view;
    }

    public void esc(ActionEvent event){
        view.showDisconnectScreen();
    }

    public void stay(ActionEvent event){
       // discStage.setScene(scene);
       // discStage.show();
        disp.show(discStage);
    }
}
