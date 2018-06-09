package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverSocket;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.EventMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowAnswerMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListenerClientManager {

    private BufferedReader reader;
    private ClientManagerSocket clientManagerSocket;
    private Socket socket;

    private static final Logger LOGGER = Logger.getLogger(ListenerClientManager.class.getName());

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
        String message = null;
        while (message == null)
            message = receiveMessage();
        if (message!= null){
            LOGGER.log(Level.SEVERE,"Message " + message);
            recognize(message);

            message = null;
        }
    }

    //parsing of messages
    public void recognize(String message){

        if (message == null)
            return;
        DataMessage dataMessage = new DataMessage(null,null);
        String op = dataMessage.parserFirstElement(message);
        if (op.equals("login")){
            LOGGER.log(Level.SEVERE,"In login body");
            DataMessage message1 = DataMessage.deserializeDataMessage(message);
            clientManagerSocket.login(message1.getField1());
        }
        else if (op.equals("connected")){
            LOGGER.log(Level.SEVERE,"In get id body");
            clientManagerSocket.sendAvailableId();
        }
        else if(op.equals("send_windowcard")){
            LOGGER.log(Level.SEVERE,"In send window card body");
            WindowAnswerMessage answerMessage = WindowAnswerMessage.deserializeWindowAnswerMessage(message);
            clientManagerSocket.chosenWindowPattern(answerMessage.getActionEventWindow());
        }
        else if(op.equals("send_actionevent_from_view")){

            LOGGER.log(Level.SEVERE,"In send actionevent from view body");
            EventMessage eventMessage = EventMessage.deserializeEventMessage(message);
            clientManagerSocket.sendActionEventFromView(eventMessage.getActionEvent());
        }

    }

}

