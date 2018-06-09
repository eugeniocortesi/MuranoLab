package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class windowPatternController {

    @FXML
    private MyRectangleController card1Controller;
    @FXML
    private MyRectangleController card2Controller;
    @FXML
    private MyRectangleController card3Controller;
    @FXML
    private MyRectangleController card4Controller;

    public void setCardLable(ArrayList<WindowPatternCard> wpc){
        if(wpc.size()==4){
            card1Controller.setLable(wpc.get(0).getTitle());
            card2Controller.setLable(wpc.get(1).getTitle());
            card3Controller.setLable(wpc.get(2).getTitle());
            card4Controller.setLable(wpc.get(3).getTitle());
        }
    }

    public void setLable(String s){
        //card1Controller.setLable(s);
        System.out.println(card1Controller);
    }

    public void windowChosen(ActionEvent actionEvent){
    }

}
