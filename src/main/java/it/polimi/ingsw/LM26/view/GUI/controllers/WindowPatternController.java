package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.view.GUI.View;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class WindowPatternController {

    private ArrayList<WindowPatternCard> wpc;
    private String user;

    @FXML
    private ImageView c1;
    @FXML
    private ImageView c2;
    @FXML
    private ImageView c3;
    @FXML
    private ImageView c4;
    @FXML
    private ToggleGroup choice;
    @FXML
    private Button ok;

    public void setCardLable(ArrayList<WindowPatternCard> wpc, String user){
        Image i1, i2, i3, i4;
        ImageManager imageManager;
        this.wpc=wpc;
        this.user=user;
        imageManager=new ImageManager();
        if(wpc.size()==4){
            i1=imageManager.getPatternCard(wpc.get(0).getTitle());
            c1.setImage(i1);
            i2=imageManager.getPatternCard(wpc.get(1).getTitle());
            c2.setImage(i2);
            i3=imageManager.getPatternCard(wpc.get(2).getTitle());
            c3.setImage(i3);
            i4=imageManager.getPatternCard(wpc.get(3).getTitle());
            c4.setImage(i4);
        }
    }

    public void cardChoice(ActionEvent event){
        ToggleButton selected=(ToggleButton)choice.getSelectedToggle();
        if(selected!=null){
            int n=Character.getNumericValue(selected.getText().charAt(0));
            ok.setDisable(true);
            ActionEventWindow aew= new ActionEventWindow(user, wpc.get(n-1));
            View.getClientView().chosenWindowPattern(aew);
        }
    }



}
