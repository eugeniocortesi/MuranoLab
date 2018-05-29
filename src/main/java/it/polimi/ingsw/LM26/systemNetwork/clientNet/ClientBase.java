package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;

public class ClientBase {

    ConsoleStrings cli;
    ClientView clientView;
    private DataClientImplementation dataClientImplementation;
    private DataClientConfiguration dataClientConfiguration;

    boolean connection; //true for RMI, false for Socket

    public ClientBase(){

        dataClientImplementation = new DataClientImplementation();
        dataClientConfiguration = dataClientImplementation.implementation();
        System.out.println("SocketPort " +dataClientConfiguration.getClientSOCKETPORT()+ " ClientRMI " + dataClientConfiguration.getClientRMIPORT()
                + " ServerRMI "+ dataClientConfiguration.getServerRMIPORT());

        /*cli = new ConsoleStrings(this);
        cli.showNetChoise();
        if (connection == true){
            clientView = new ClientViewRMI(this, dataClientConfiguration);
        }

        else if (connection == false)
            clientView = new ClientViewSocket(this, dataClientConfiguration);
        clientView.connect();*/
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }
}
