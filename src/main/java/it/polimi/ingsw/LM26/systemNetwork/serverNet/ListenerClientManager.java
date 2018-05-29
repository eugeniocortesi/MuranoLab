package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListenerClientManager {

    private BufferedReader reader;
    private ClientManagerSocket clientManagerSocket;
    private Socket socket;

    public ListenerClientManager(ClientManagerSocket managerSocket, Socket socket){

        clientManagerSocket= managerSocket;
        this.socket= socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }catch (IOException e) {

        }
    }

    public String receiveMessage() {

        String messageReceived = null;
        try {

            messageReceived = (String) this.reader.readLine();
            if (messageReceived == null){

                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO gestire operazioni bloccanti !!

        return messageReceived;
    }

    public void listen() {
        try {
            String message = null;
            //while (message == null) {

                message = receiveMessage();
                if (message!= null){
                    System.out.println("Message " + message);
                    recognize(message);
                //}

                message = null;
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

    //parsing of messages
    public void recognize(String message){

        if (message == null)
            return;
        DataMessage dataMessage = new DataMessage(null,null);
        String op = dataMessage.parserFirstElement(message);
        if (op.equals("login")){
            System.out.println("In login body");
            DataMessage message1 = DataMessage.deserializeDataMessage(message);
            clientManagerSocket.login(message1.getField1());
        }
        if (op.equals("connected")){
            System.out.println("In get id body");
            clientManagerSocket.sendAvailableId();
        }

    }

}

