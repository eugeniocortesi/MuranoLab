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

/**
 * ListenerClientManager class
 * @author Chiara Criscuolo
 * It listens and recognises every message that arrives from Client
 * Sends it to ClientManager
 */
public class ListenerClientManager extends Thread {

    private BufferedReader reader;

    private ClientManagerSocket clientManagerSocket;

    private boolean inAction;

    private static final Logger LOGGER = Logger.getLogger(ListenerClientManager.class.getName());

    /**
     * Constructor
     * @param managerSocket reference to ClientManager
     * @param socket socket
     */

    public ListenerClientManager(ClientManagerSocket managerSocket, Socket socket){

        clientManagerSocket= managerSocket;

        LOGGER.setLevel(Level.FINE);

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }catch (IOException e) {

            System.err.println("Impossible to create a new reader, reset Server");
        }

        inAction = true;
    }

    /**
     * Method that receive message reading from Buffer
     * @return message String
     */

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

        return messageReceived;
    }

    /**
     * Calls receiveMessage when it is not null
     */

    private void listen() {

        String message = null;

        while (message == null && inAction) {
            message = receiveMessage();
        }

        if (message!= null){

            recognize(message);

            run();

            message = null;
        }
    }

    /**
     * Method that parses message using first field
     * @param message String
     */

    private void recognize(String message){

        if (message == null)

            return;

        DataMessage dataMessage = new DataMessage(null,null);

        String op = dataMessage.parserFirstElement(message);

        switch (op) {
            case "login":
                LOGGER.log(Level.SEVERE, "In login body");

                DataMessage message1 = DataMessage.deserializeDataMessage(message);

                clientManagerSocket.login(message1.getField1());

                break;

            case "connected":
                LOGGER.log(Level.SEVERE, "In get id body");

                clientManagerSocket.sendAvailableId();

                break;

            case "send_windowcard":
                LOGGER.log(Level.SEVERE, "In send window card body");

                WindowAnswerMessage answerMessage = WindowAnswerMessage.deserializeWindowAnswerMessage(message);

                clientManagerSocket.chosenWindowPattern(answerMessage.getActionEventWindow());

                break;

            case "send_actionevent_from_view":

                LOGGER.log(Level.SEVERE, "In send actionevent from view body");

                EventMessage eventMessage = EventMessage.deserializeEventMessage(message);

                clientManagerSocket.sendActionEventFromView(eventMessage.getActionEvent());

                break;
            case "disconnect":

                LOGGER.log(Level.SEVERE, "In disconnect client body");

                DataMessage dataMessage1 = DataMessage.deserializeDataMessage(message);

                clientManagerSocket.disconnect(dataMessage1.getField1());

                break;

            case "pong":

                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        clientManagerSocket.pong();
                    }
                });
                t2.start();

                break;

            default:

                LOGGER.log(Level.WARNING, "Message not recognized");
                break;
        }

    }

    /**
     * Called by start it listen while inAction is true
     */

    @Override
    public void run(){

        //System.out.println("RUN IN ACTION" +inAction);
        if(inAction)

            listen();
    }

    /**
     * Called when listenerClientManger has to end to listen
     */

    public void end(){

        inAction= false;
    }
}

