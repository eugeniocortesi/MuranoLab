package it.polimi.ingsw.LM26.view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class SquareController {

    @FXML
    private Label label;

    @FXML
    private Rectangle square;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setColor(char color){
        switch (color){
            case 'y':{ square.setFill(Color.YELLOW); break;}
            case 'g':{ square.setFill(Color.GREEN); break;}
            case 'b':{ square.setFill(Color.BLUE); break;}
            case 'v':{ square.setFill(Color.VIOLET); break;}
            case 'r':{ square.setFill(Color.RED); break;}
            case 'w':{ square.setFill(Color.WHITE); break;}
        }
    }

    public Paint getColor(){
        return square.getFill();
    }
}
