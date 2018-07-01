package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverSocket;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.EventMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowAnswerMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListenerClientManager extends Thread {

    private BufferedReader reader;
    private ClientManagerSocket clientManagerSocket;
    private Socket socket;
    private boolean inAction;

    private static final Logger LOGGER = Logger.getLogger(ListenerClientManager.class.getName());

    public ListenerClientManager(ClientManagerSocket managerSocket, Socket socket){

        clientManagerSocket= managerSocket;
        this.socket= socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }catch (IOException e) {

        }
        inAction = true;
    }

    public String receiveMessage() {

        String messageReceived = null;
        try {

            messageReceived = (String) this.reader.readLine();
            if (messageReceived == null) {

                return null;
            }
        }catch (SocketException se){
            System.err.println("Connection reset");
            end();
        } catch (IOException e) {
            System.err.println("IOException socket reading");
            end();
        }
        //TODO gestire operazioni bloccanti !!

        return messageReceived;
    }

    public void listen() {
        String message = null;
        while (message == null && inAction)
            message = receiveMessage();
        if (message!= null){
            //LOGGER.log(Level.SEVERE,"Message " + message);
            recognize(message);

            run();
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
        else if(op.equals("disconnect")){

            LOGGER.log(Level.SEVERE,"In disconnect client body");
            DataMessage dataMessage1 = DataMessage.deserializeDataMessage(message);
            clientManagerSocket.disconnect(dataMessage1.getField1());
        }
        else if(op.equals("pong")){
            //LOGGER.log(Level.SEVERE,"In pong body");
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    clientManagerSocket.pong();
                }
            });
            t2.start();

        }
        else {
            LOGGER.log(Level.WARNING,"Message not recognized");
        }

    }

    @Override
    public void run(){
        if(inAction)
            listen();
    }

    public void end(){

        inAction= false;
    }
}

