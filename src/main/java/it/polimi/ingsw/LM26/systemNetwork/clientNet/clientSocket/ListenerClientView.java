package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientSocket;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ModelMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ListenerClientView class
 * @author Chiara Criscuolo
 * It has to listen every message that arrives from Server and recognise it
 */

public class ListenerClientView extends Thread {

    private BufferedReader reader;

    private ClientViewSocket clientView;

    private static final Logger LOGGER = Logger.getLogger(ListenerClientView.class.getName());

    /**
     * Constructor
     * @param clientView instance of Client View that manages the connection
     * @param socket port to receive messages
     */

    public ListenerClientView(ClientViewSocket clientView, Socket socket) {

        this.clientView = clientView;

        LOGGER.setLevel(Level.SEVERE);

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {

            System.err.println("Error in socket get Stream");
        }
    }

    /**
     * Method called by start
     * Calls listen
     */

    @Override
    public void run(){
        listen();
    }

    /**
     * Method that controls if the received message is null
     * @return message string arrived from Server
     */

    private String receiveMessage(){

        String messageReceived = null;
        try {

            messageReceived = (String) this.reader.readLine();
            if (messageReceived == null){

                return null;
            }
        } catch (IOException e) {

            System.err.println("Error in receive message");

            return null;
        }

        return messageReceived;
    }

    /**
     * Method that receive message and calls recognise if it is not null
     */

    private void listen() {

        LOGGER.log(Level.SEVERE,"I'm listening");

        String message = null;

            while(message == null) {

                message = receiveMessage();
            }
            if (message!= null){

                LOGGER.log(Level.SEVERE,"Message " + message);

                Thread t = new Thread(new MyRunnableRecognize(message));

                t.start();

                listen();
            }
            message = null;
    }

    /**
     * Method that parses messages and calls the right method for each one
     * The parsing uses the first field of the message
     * @param message message to be parsed
     */
    private void recognize(String message){

        if (message == null)

            return;

        DataMessage dataMessage = new DataMessage(null,null);

        String op = dataMessage.parserFirstElement(message);

        if(op == null)

            return;

        else if (op.equals("requested_login")){

            LOGGER.log(Level.SEVERE,"In login body");

            clientView.requestedLogin();
        }
        else if(op.equals("logged")){

            LOGGER.log(Level.SEVERE,"In logged body");

            clientView.logged(true,dataMessage.getField1());
        }
        else if(op.equals("not_logged")){

            LOGGER.log(Level.SEVERE,"In not logged body");

            clientView.logged(false,dataMessage.getField1());
        }
        else if(op.equals(("too_many_users"))){

            System.out.println("In too many users body");

            clientView.tooManyUsers();
        }
        else if(op.equals("connected")){

            LOGGER.log(Level.SEVERE,"In client connected body");

            ConnectMessage connectMessage = ConnectMessage.deserializeConnectMessage(message);

            int id = connectMessage.getField1();

            clientView.getAvailableId(id);

        }else if(op.equals("send_windowlist")){

            LOGGER.log(Level.SEVERE, "In send window list body");

            WindowInitialMessage windowInitialMessage = WindowInitialMessage.deserializeWindowInitialMessage(message);

            String user= windowInitialMessage.getUser();

            int id = windowInitialMessage.getId();

            ArrayList<WindowPatternCard> windowPatternCards = windowInitialMessage.getWindowlist();

            clientView.choseWindowPattern(user, id, windowPatternCards);
        }
        else if(op.equals("send_model")){
            LOGGER.log(Level.SEVERE, "In send model body");

            ModelMessage modelMessage = ModelMessage.deserializeModelMessage(message);

            Model model = ModelMessage.deserializeModel(modelMessage.getModel());

            clientView.notify(model);
        }
        else if(op.equals("send_answer_from_controller")){

            LOGGER.log(Level.SEVERE, "In send answer from controller body");

            DataMessage dataMessage1 = DataMessage.deserializeDataMessage(message);

            String field1 = dataMessage1.getField1();

            clientView.sendAnswerFromController(field1);
        }
        else if(op.equals("send_privatecard")){

            LOGGER.log(Level.SEVERE, "In send private card body");

            PrivateCardMessage privateCardMessage = PrivateCardMessage.deserializeEventMessage(message);

            ObjectivePrivateCard objectivePrivateCard = privateCardMessage.getPrivateCard();

            clientView.sendPrivateCard(objectivePrivateCard);
        }

        else if(op.equals("send_beginturnmessage")){

            LOGGER.log(Level.SEVERE, "In send begin turn message body");

            BeginTurnMessage beginTurnMessage = BeginTurnMessage.deserializeDataMessage(message);

            PlayerZone playerZone = beginTurnMessage.getPlayerZone();

            String name = beginTurnMessage.getUsername();

            clientView.sendBeginTurn(name, playerZone);
        }
        else if(op.equals("disconnected")){

            LOGGER.log(Level.SEVERE, "In send disconnected message body");

            clientView.disconnected();
        }
        else if(op.equals("added_player")){

            LOGGER.log(Level.SEVERE, "In added player message body");

            DataMessage dataMessage1 = DataMessage.deserializeDataMessage(message);

            clientView.sendAddedPlayer(dataMessage1.getField1());
        }
        else if(op.equals("send_currentmenu")){

            LOGGER.log(Level.SEVERE, "In send current menu message body");

            DataMessage dataMessage1 = DataMessage.deserializeDataMessage(message);

            clientView.sendCurrentMenu(dataMessage1.getField1());
        }
        else if(op.equals("ping")){

            clientView.pong();

        }
        else if(op.equals("endGame")){

            LOGGER.log(Level.SEVERE, "In end game message body");

            EndMessage m = EndMessage.deserializeEndMessage(message);

            clientView.endGame(m.getUsername(), m.getScore(), m.getWinner(), m.getScoreWinner());
        }
        else {

            LOGGER.log(Level.WARNING,"Message not recognized");
        }

    }

    /**
     * Class that calls recognise to parse message
     */
    private class MyRunnableRecognize implements Runnable {

        volatile String message;

        /**
         * Constructor
         * @param message to parse
         */

        MyRunnableRecognize(String message) {
            this.message = message;
        }

        /**
         * Method that calls recognise to parse message
         */

        @Override
        public void run() {
            recognize(message);
        }
    }
}
