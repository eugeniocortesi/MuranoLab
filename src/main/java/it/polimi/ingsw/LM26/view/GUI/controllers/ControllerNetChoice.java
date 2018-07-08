package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.fileConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI.ClientViewRMI;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientSocket.ClientViewSocket;
import it.polimi.ingsw.LM26.view.GUI.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ControllerNetChoice {

    @FXML
    private Button ok;
    @FXML
    private RadioButton socket;
    @FXML
    private RadioButton rmi;
    @FXML
    private ToggleGroup connection;

    private DataClientConfiguration dClientConfig;
    private View view;


    public void setClientViewBase(View view){
        this.view=view;
        dClientConfig = new DataClientConfiguration();
        dClientConfig = dClientConfig.implementation();
    }


    //it returns the label on the radio button
    public void handleRmiSocketButton(ActionEvent event){
        RadioButton selected= (RadioButton)connection.getSelectedToggle();
        ok.setDisable(true);
        ClientView clientView;

        if(selected.getText().equals("RMI")){
            clientView = new ClientViewRMI(view, dClientConfig );
            View.setClientView( clientView);
            View.getClientBase().setConnection(true);
        }
        else{
            clientView = new ClientViewSocket(view, dClientConfig);
            View.setClientView(clientView);
            View.getClientBase().setConnection(false);
            System.out.println("CREated socket");
        }
        View.getClientView().connect();
        view.register(clientView);
    }
}
