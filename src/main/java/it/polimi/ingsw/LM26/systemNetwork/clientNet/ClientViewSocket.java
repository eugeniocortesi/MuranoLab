package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.networkServer.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.networkServer.clientConfiguration.DataClientImplementation;

import java.io.*;
import java.net.Socket;

public class ClientViewSocket implements ClientView {

    private ListenerClientView listenerClientView;
    private ConcreteClientView concreteClientView;
    private int SOCKETPORT;

    private static String address;
    private Socket socket;
    private BufferedReader inSocket;
    private PrintWriter outSocket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private String username;

    public ClientViewSocket(ConcreteClientView concreteClientView, DataClientConfiguration data){
        listenerClientView = new ListenerClientView();
        this.concreteClientView = concreteClientView;
        SOCKETPORT = data.getClientSOCKETPORT();
        address = data.getIp();

        try {
            connect();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            // Always close it:
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }


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

    @Override
    public void requestedLogin() {

    }

    @Override
    public void login(String name) {

    }

    @Override
    public void logged(Boolean l, String name) {

    }

    @Override
    public void tooManyUsers() {

    }

    @Override
    public void disconnect() {

    }
}
