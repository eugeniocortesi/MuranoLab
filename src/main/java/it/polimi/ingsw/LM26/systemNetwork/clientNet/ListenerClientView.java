package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.DataEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListenerClientView {

    DataEvent dataEvent;
    private BufferedReader reader;
    private ClientViewSocket clientView;
    private Socket socket;

    public ListenerClientView(ClientViewSocket clientView, Socket socket) {

        this.clientView = clientView;
        this.socket = socket;

        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            //listen();
        } catch (IOException e) {

        }
    }

    public String receiveMessage(){

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
        System.out.println("I'm listening");
        String message = null;
        //while (message == null) {

            message = receiveMessage();
            if (message!= null){
                System.out.println("Message " + message);
                recognize(message);
            }
            message = null;
        //}
    }

    //parsing of messages
    public void recognize(String message){

        if (message == null)
            return;
        DataMessage dataMessage = new DataMessage(null,null);
        String op = dataMessage.parserFirstElement(message);
        if (op.equals("requested_login")){
            System.out.println("In login body");
            clientView.requestedLogin();
        }
        else if(op.equals("logged")){
            System.out.println("In logged body");
            clientView.logged(true,dataMessage.getField1());
        }
        else if(op.equals("not_logged")){
            System.out.println("In not logged body");
            clientView.logged(false,dataMessage.getField1());
        }
        else if(op.equals(("too_many_users"))){
            System.out.println("In too many users body");
            clientView.tooManyUsers();
        }
        else if(op.equals("connected")){
            System.out.println("In client connected body");
            ConnectMessage connectMessage = ConnectMessage.deserializeConnectMessage(message);
            int id = connectMessage.getField1();
            clientView.getAvailableId(id);
        } else {
            System.out.println("Message not recognized");
        }

    }
}
