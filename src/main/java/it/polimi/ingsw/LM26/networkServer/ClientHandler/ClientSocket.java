package it.polimi.ingsw.LM26.networkServer.ClientHandler;
import it.polimi.ingsw.LM26.networkServer.Data.ClientLoginData;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocket implements ClientInt {

    private Socket socket;
    private DataOutputStream writer;
    private BufferedReader reader;
    private boolean logged;
    private String user;
    private int id; //created in costruction of connection acepter

     public boolean isLogged() {
         return logged;
     }

     public void setLogged(boolean logged) {
         this.logged = logged;
     }

     public ClientSocket(Socket clientSocket){

        this.socket = clientSocket;
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
        //TODO grr bloccante !!
        if (messageReceived == null){

            return null;
        }
        else return messageReceived;
    }

        public synchronized void disconnect(){
        try{
            socket.close();
        }catch (Exception e){
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
        finally {
            try{
                socket.close();
            }catch(IOException ex){
                System.err.println("Socket not closed");
            }
        }

    }

    public void run() {

        try {
            login();

            disconnect();

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

            System.out.println("Entrato nel ciclo");
            /*ClientMessageData msg = new ClientMessageData("requested_login");
            String msg_str = msg.serialize();
            System.out.println(msg_str);
            this.sendMessage(msg_str);*/

            String msg = receiveMessage();
            System.out.println(msg);
            ClientLoginData messageData = ClientLoginData.deserialize(msg);
            System.out.println("username: " + messageData.getUsername());
            this.user = messageData.getUsername();

            //call method to control login??
            //server.getConnectionAcepter.controluser
            //server.getConnectionAcepter.addPlayer ???

            //client vuole true
            sendMessage("Logged true");
            this.setLogged(true);


        }
    }

    public void start() {
         run();
    }
}
