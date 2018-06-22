package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientBase;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientInt;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
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

    private ClientView clientView;
    private ClientInt clientBase;
    private DataClientConfiguration dClientConfig;
    private DataClientImplementation dClientImplem;
    private View view;


    public void setClientViewBase(ClientView cView, ClientInt cBase, View view){
        this.clientBase=cBase;
        this.clientView=cView;
        this.view=view;
        dClientImplem = new DataClientImplementation();
        dClientConfig = dClientImplem.implementation();
        System.out.println("SocketPort " +dClientConfig.getClientSOCKETPORT()+ " ClientRMI " + dClientConfig.getClientRMIPORT()
                + " ServerRMI "+ dClientConfig.getServerRMIPORT());
    }


    //it returns the label on the radio button
    public void handleRmiSocketButton(ActionEvent event){
        RadioButton selected= (RadioButton)connection.getSelectedToggle();
        System.out.println(selected.getText());
        ok.setDisable(true);
        if(selected.getText().equals("RMI")){
            clientView = new ClientViewRMI(view, dClientConfig );
            clientBase.setConnection(true);
        }
        else{
            clientView = new ClientViewSocket(view, dClientConfig);
            clientBase.setConnection(false);
        }
        clientView.connect();
    }
}
