package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.systemNetwork.netConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.netConfiguration.DataClientImplementation;
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
        System.out.println("SocketPort " +dClientConfig.getClientSOCKETPORT()+ " ClientRMI " + dClientConfig.getClientRMIPORT()
                + " ServerRMI "+ dClientConfig.getServerRMIPORT());
    }


    //it returns the label on the radio button
    public void handleRmiSocketButton(ActionEvent event){
        RadioButton selected= (RadioButton)connection.getSelectedToggle();
        ok.setDisable(true);
        if(selected.getText().equals("RMI")){
            View.setClientView( new ClientViewRMI(view, dClientConfig ));
            View.getClientBase().setConnection(true);
        }
        else{
            View.setClientView(new ClientViewSocket(view, dClientConfig));
            View.getClientBase().setConnection(false);
        }
        View.getClientView().connect();
    }
}
