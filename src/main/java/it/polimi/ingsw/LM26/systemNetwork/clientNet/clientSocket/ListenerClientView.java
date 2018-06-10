package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientSocket;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ModelMessage;
import it.polimi.ingsw.LM26.systemNetwork.DataEvent;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListenerClientView {

    DataEvent dataEvent;
    private BufferedReader reader;
    private ClientViewSocket clientView;
    private Socket socket;

    private static final Logger LOGGER = Logger.getLogger(ListenerClientView.class.getName());


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
        LOGGER.log(Level.SEVERE,"I'm listening");
        String message = null;
        //while (message == null) {
            while(message == null) {
                //LOGGER.log(Level.INFO,"Message null: " + message);
                message = receiveMessage();
            }
            if (message!= null){
                LOGGER.log(Level.INFO,"Message " + message);
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
            Model model = ModelMessage.deserializeModelMessage(message);
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

        else {
            LOGGER.log(Level.WARNING,"Message not recognized");
        }

        listen();
    }
}
