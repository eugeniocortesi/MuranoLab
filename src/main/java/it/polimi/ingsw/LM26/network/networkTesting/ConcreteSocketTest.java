package it.polimi.ingsw.LM26.network.networkTesting;

import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;

import java.io.*;
import java.net.Socket;

public class ConcreteSocketTest {
    private int SOCKETPORT;
    private final static String address = "localhost";
    private DataClientImplementation dataClientImplementation;
    private Socket socket;
    private BufferedReader inSocket;
    private PrintWriter outSocket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private String username;
    private boolean logged;

    public ConcreteSocketTest() {
        logged = false;
        System.out.println("ClientImplementationSocket avviato");
        this.dataClientImplementation = new DataClientImplementation();
        DataClientConfiguration dataClientConfiguration = this.dataClientImplementation.implementation();
        SOCKETPORT = dataClientConfiguration.getClientSOCKETPORT();

        try {
            executeBody();
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

    private void executeBody() {
        try {
            connect();
            login();
            waitTime();

            //close();
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

    private void connect() {
        try {
            System.out.println("Il client tenta di connettersi");

            socket = new Socket(address, SOCKETPORT);
            //canali di comunicazione
            inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);

            System.out.println("ClientImplementationSocket connesso");
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

    private void login() {
        try {

            while (logged == false) {
                outVideo.println("Inserire login:");
                String userPlayer = inKeyboard.readLine();

                System.out.println(userPlayer);

                DataMessage msg = new DataMessage("login", userPlayer);
                msg.dump();
                String msg_str = msg.serializeClassMessage();
                System.out.println(msg_str);
                this.outSocket.println(msg_str);
                /*outSocket.println("login");
                outSocket.flush();
                outSocket.println(username);
                outSocket.flush();*/
                logged = checklogin ();

                if (logged == true) {
                    outVideo.println("Login effettuato correttamente");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }

    public boolean checklogin() {

        try {
            String serveranswer = this.inSocket.readLine();
            System.out.println(serveranswer);
            DataMessage messageData = DataMessage.deserializeDataMessage(serveranswer);
            System.out.println("username: " + messageData.getField1());
            if (messageData.getOperation().equals("not_logged")){
                System.out.println("Name already present");
                return false;
            }
            else if(messageData.getOperation().equals(("too_many_users"))){

                System.out.println("Already 4 players!");
                return false;

            }
            else if(messageData.getOperation().equals("already_logged")){
                System.out.println("Already logged with this username" + messageData.getField1());
            }

            else {
                System.out.println("Client recognized");
                this.username = messageData.getField1();
                //prendi tutti i campi necessari al client
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void waitTime(){
        while(true);
    }

    //TODO remove
    public static void main(String[] args) throws IOException, InterruptedException
    {
        ConcreteSocketTest socketTest = new ConcreteSocketTest();
    }

    private void close()  {

        try{
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }
}
