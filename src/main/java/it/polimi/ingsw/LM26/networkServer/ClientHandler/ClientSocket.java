package it.polimi.ingsw.LM26.networkServer.ClientHandler;
import it.polimi.ingsw.LM26.networkServer.Data.ClientLoginData;
import it.polimi.ingsw.LM26.networkServer.Server.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocket implements ClientInt {

    private Socket socket;
    private DataOutputStream writer;
    private BufferedReader reader;
    private Server server;
    private boolean logged;
    private String user;
    private int id; //created in costruction of connection acepter

    public String getUsername() {
        return this.user;
    }

     public boolean isLogged() {
         return logged;
     }

     public void setLogged(boolean logged) {
         this.logged = logged;
     }

     public ClientSocket(Socket clientSocket, Server server){

        this.socket = clientSocket;
        this.server= server;
        this.logged = false;
        this.user = null;
        this.id = 0;
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

        public synchronized void disconnect(){

            while(this.logged) {

                System.out.println("Entrato nel ciclo disconnect");
                String msg = receiveMessage();
                System.out.println(msg);
                ClientLoginData messageData = ClientLoginData.deserialize(msg);
                System.out.println("username: " + messageData.getUsername());
                this.user = messageData.getUsername();

                //server.getConnectionAcepter.controluser
                //server.getConnectionAcepter.addPlayer ???

                //ANSWER SERVER

                ClientLoginData message = new ClientLoginData("disconnected", this.user);
                String disconnectedMessage = message.serializeLoginMessage();
                sendMessage(disconnectedMessage);
                this.setLogged(false);


                try {
                    socket.close();
                    server.removerConnectionsClient(this);
                } catch (Exception e) {
                    System.out.println("Client socket: " + this.user + " not closed");
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        System.err.println("Socket not closed");
                        server.removerConnectionsClient(this);
                    }
                }
            }

    }

    public void run(){

        try {
            login();

            //disconnect();

        } catch (Exception e) {
        } finally {

            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }



    public synchronized void login(){

         System.out.println("Entrato in clientSocket");
        while(!this.logged){

            System.out.println("Entrato nel ciclo login");

            String msg = receiveMessage();
            System.out.println(msg);
            ClientLoginData messageData = ClientLoginData.deserialize(msg);
            System.out.println("username: " + messageData.getUsername());
            if( server.checkUsername(messageData.getUsername())){
                this.user = messageData.getUsername();
                ClientLoginData logged = new ClientLoginData("logged", this.user);
                String anslogin = logged.serializeLoginMessage();
                sendMessage(anslogin);
                this.setLogged(true);
            }

            else{

                System.out.println("This Username is already present");
                ClientLoginData messageNotLogged = new ClientLoginData("not_logged", messageData.getUsername());
                String answerNotLogged = messageNotLogged.serializeLoginMessage();
                System.out.println(answerNotLogged);
                sendMessage(answerNotLogged);

            }
        }
    }

    public void start() {
         run();
    }
}
