package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;

public class ClientBase {

    ConsoleStrings cli;
    ConsoleStrings concreteClientView;

    boolean connection; //true for RMI, false for Socket
    String username;

    public ClientBase(){



        cli = new ConsoleStrings(this);
        /*concreteClientView = new ConcreteClientView(cli);

        //Warning now is initialScreen()
        cli.showNetChoise();
        if (connection == true){
            //ClientView has to take ClientBase
            clientView = new ClientViewRMI(this, dataClientConfiguration);
        }

        else if (connection == false)
            clientView = new ClientViewSocket(this, dataClientConfiguration);
        clientView.connect();*/
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
