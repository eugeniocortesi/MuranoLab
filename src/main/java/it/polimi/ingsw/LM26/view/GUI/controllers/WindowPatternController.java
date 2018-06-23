package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.View;
import it.polimi.ingsw.LM26.view.GUI.controllers.ControllerCardRec;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;

public class WindowPatternController {

    private ArrayList<WindowPatternCard> wpc;
    private String user;

    @FXML
    private ControllerCardRec card1Controller;
    @FXML
    private ControllerCardRec card2Controller;
    @FXML
    private ControllerCardRec card3Controller;
    @FXML
    private ControllerCardRec card4Controller;
    @FXML
    private ToggleGroup choice;
    @FXML
    private Button ok;

    public void setCardLable(ArrayList<WindowPatternCard> wpc, String user){
        this.wpc=wpc;
        this.user=user;
        if(wpc.size()==4){
            card1Controller.setLable(wpc.get(0).getTitle());
            card2Controller.setLable(wpc.get(1).getTitle());
            card3Controller.setLable(wpc.get(2).getTitle());
            card4Controller.setLable(wpc.get(3).getTitle());
        }
    }

    public void cardChoice(ActionEvent event){
        ToggleButton selected=(ToggleButton)choice.getSelectedToggle();
        if(selected!=null){
            int n=Character.getNumericValue(selected.getText().charAt(0));
            //System.out.println(n);
            ok.setDisable(true);
            ActionEventWindow aew= new ActionEventWindow(user, wpc.get(n-1));
            View.getClientView().chosenWindowPattern(aew);
        }
    }



}
