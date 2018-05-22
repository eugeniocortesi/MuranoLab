package it.polimi.ingsw.LM26.network.server.socket;

import com.sun.deploy.util.SessionState;
import it.polimi.ingsw.LM26.network.server.ClientHandlerInt;
import it.polimi.ingsw.LM26.network.server.ServerImpl;
import it.polimi.ingsw.LM26.network.server.socket.dataProtocolSocket.DataMessage;
import it.polimi.ingsw.LM26.networkServer.Data.ClientLoginData;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandlerSocketImpl extends Thread implements ClientHandlerInt {

    private boolean logged;
    private Socket socket;
    private DataOutputStream writer;
    private BufferedReader reader;
    private ServerImpl server;

    public ClientHandlerSocketImpl(Socket clientSocket, ServerImpl server){
        logged = false;
        socket = clientSocket;
        this.server= server;
        try {

            this.writer = new DataOutputStream(this.socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        }catch (IOException e) {

        }
    }

    public void sendMessage(String messageSent) {

        if(messageSent == null ){
            return;
        }
        try{
            this.writer.writeBytes(messageSent + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {

        String messageReceived = null;
        try {
            messageReceived = (String) this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO gestire operazioni bloccanti !!
        if (messageReceived == null){

            return null;
        }
        else return messageReceived;
    }

    public void run(){

        try {
            String message = null;
            while(message==null){

               message = receiveMessage();
                System.out.println("Message " +message);
                recognize(message);
                message= null;
            }


        } catch (Exception e) {
        } finally {

            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void recognize(String message){

        if (message == null)
            return;
        DataMessage dataMessage = new DataMessage(null,null);
        String op = dataMessage.parserFirstElement(message);
        if (op.equals("login")){
            System.out.println("In login body");
            DataMessage message1 = DataMessage.deserializeDataMessage(message);
            login(message1.getField1());
        }

    }

    //TODO SEND MESSAGE
    public void login(String username) {

        if(logged == true){
            System.out.println("Already logged");
            DataMessage message = new DataMessage("already_logged", username);
            message.dump();
            sendMessage(message.serializeDataMessage());
        }
        else{
            if(server.checkLogin(username) && server.checkNumberUser()){
                server.getUserConnections().put(username, this);
                System.out.println("Hashmap size : " +server.getUserConnections().size());
                System.out.println(username + " logged");
                this.logged = true;
                //
                DataMessage message = new DataMessage("logged", username);
                message.dump();
                sendMessage(message.serializeDataMessage());
            }

            else if(!(server.checkLogin(username))){
                System.out.println("Name already present!");
                //
            }
            else{
                System.out.println("Too many users!");
                //
            }
        }

    }

    public void disconnect(String username) {

    }
}
