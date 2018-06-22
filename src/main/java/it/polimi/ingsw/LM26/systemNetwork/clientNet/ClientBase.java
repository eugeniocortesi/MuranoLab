package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.view.GUI.View;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;

import java.io.IOException;

public class ClientBase extends ClientInt {

    //private ConsoleStrings cli;
    //private View view;

    //ViewInterface view;
    //ConsoleStrings concreteClientView;

    //private boolean connection; //true for RMI, false for Socket
    //private String username;

    public ClientBase() {


            //cli = new ConsoleStrings(this);
            //cli = new ConsoleStrings(this);

            //Warning now is initialScreen()

            //cli.showNetChoise();
        /*
        if (connection == true){
            //ClientView has to take ClientBase
            clientView = new ClientViewRMI(this, dataClientConfiguration);
        }

        else if (connection == false)
            clientView = new ClientViewSocket(this, dataClientConfiguration);
        clientView.connect();*/
    }

    public void setView(ViewInterface view) {
        this.view = view;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }



}
