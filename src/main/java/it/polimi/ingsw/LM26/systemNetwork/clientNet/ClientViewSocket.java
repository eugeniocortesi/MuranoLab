
package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;

import java.io.*;
import java.net.Socket;

public class ClientViewSocket implements ClientView {

    private ListenerClientView listenerClientView;
    private ConsoleStrings concreteClientView;
    private int SOCKETPORT;

    private static String address;
    private Socket socket;
    private BufferedReader inSocket;
    private PrintWriter outSocket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private String username;
    private int id;

    public ClientViewSocket(ConsoleStrings concreteClientView, DataClientConfiguration data){

        this.concreteClientView = concreteClientView;
        SOCKETPORT = data.getClientSOCKETPORT();
        address = data.getIp();

        //listenerClientView.listen();
    }

    @Override
    public void connect() {

        try {
            System.out.println("I'm trying to connect");

            socket = new Socket(address, SOCKETPORT);
            //canali di comunicazione
            inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
            listenerClientView = new ListenerClientView(this, socket);
            connected();
            listenerClientView.listen();

            System.out.println("ClientImplementationSocket connected");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void connected(){
        System.out.println("Client connected");
        ConnectMessage connectMessage = new ConnectMessage("connected", 0);
        connectMessage.dump();
        outSocket.println(connectMessage.serializeConnectMessage());
    }

    public void getAvailableId(int id){
        this.id= id;
        requestedLogin();
    }


    @Override
    public void requestedLogin() {
        concreteClientView.showLoginScreen();

    }

    @Override
    public void login(String name) {
        DataMessage message = new DataMessage("login", name);
        message.dump();
        outSocket.println(message.serializeDataMessage());
        listenerClientView.listen();
    }

    @Override
    public void logged(Boolean l, String name) {
        if (l==true){
            this.username = name;
            concreteClientView.showLoggedScreen();
        }
        else{
            concreteClientView.showAlreadyLoggedScreen();
        }
    }

    @Override
    public void tooManyUsers() {
        concreteClientView.showTooManyUsersScreen();
    }

    @Override
    public void disconnect() {
        concreteClientView.showDisconnectScreen();
    }
}