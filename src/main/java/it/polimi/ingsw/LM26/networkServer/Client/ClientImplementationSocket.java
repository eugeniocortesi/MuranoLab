package it.polimi.ingsw.LM26.networkServer.Client;


import it.polimi.ingsw.LM26.networkServer.Data.ClientLoginData;
import it.polimi.ingsw.LM26.networkServer.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.networkServer.clientConfiguration.DataClientImplementation;

import java.net.*;
import java.io.*;


public class ClientImplementationSocket {
    private int SOCKETPORT;
    private final static String address = "localhost";
    private DataClientImplementation dataClientImplementation;
    private Socket socket;
    private BufferedReader inSocket;
    private PrintWriter outSocket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private String username;

    public ClientImplementationSocket() {
        System.out.println("ClientImplementationSocket avviato");
        this.dataClientImplementation = new DataClientImplementation();
        DataClientConfiguration dataClientConfiguration = this.dataClientImplementation.implementation();
        SOCKETPORT = dataClientConfiguration.getClientSOCKETPORT();

        try {
            executeBody();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            // Always close it:
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

    private void connect() {
        try {
            System.out.println("Il client tenta di connettersi");

            socket = new Socket(address, SOCKETPORT);
            //canali di comunicazione
            inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);

            System.out.println("ClientImplementationSocket connesso");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }


    private void executeBody() {
        try {
            connect();
            login();
            waitTime();
            //gioca();
            //close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            // Always close it:
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

    /*private void gioca() {
        while (true) {
            outVideo.println("Scegliere cosa si vuol fare");
            outVideo.println("p --> partita");
            outVideo.println("r --> vedi record dei giocatori");
            outVideo.println("q --> esci");

            try {
                String scelta = inKeyboard.readLine();

                if (scelta.equals("p"))
                    partita();
                else if (scelta.equals("r"))
                    vediRecord();
                else if (scelta.equals("q")) {
                    outSocket.println("fine");
                    outVideo.println("ARRIVEDERCI!!!!!");
                    break;
                } else
                    outVideo.println("INPUT ERRATO");
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                e.printStackTrace();

                // Always close it:
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println("Socket not closed");
                }
            }
        }
    }

    private void partita() {
        try {
            //codice partite
            outVideo.println("Inizio partita\n");
            outVideo.println("Leggi i numeri, memorizzali, riscrivili\n");

            outSocket.println("partita");

            boolean continua = true;
            while (continua) {
                String sequenza = inSocket.readLine();
                outVideo.println("Sequenza: " + sequenza);
                Thread.sleep(TempoFisso + TempoVariabile * sequenza.length());
                generaBianco();
                outVideo.println("Riinserisci la sequenza:");
                sequenza = inKeyboard.readLine();
                outSocket.println(sequenza);
                continua = Boolean.valueOf(inSocket.readLine()).booleanValue();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }

    private void vediRecord() {
        try {
            //codice partite
            outVideo.println("Record dei giocatori\n");

            outSocket.println("record");
            outVideo.println("record:");

            int numGiocatori = Integer.parseInt(inSocket.readLine());

            for (int i = 0; i < numGiocatori; i++) {
                String record = inSocket.readLine();
                outVideo.println(record);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }

    private void generaBianco() {
        for (int i = 0; i < NUMSPAZI; i++)
            outVideo.println();
    }*/

    private void close() {

        ClientLoginData disconnect = new ClientLoginData("disconnect", this.username);
        String msg = disconnect.serializeLoginMessage();
        this.outSocket.println(msg);

        try {
            String messageDisconnected = inSocket.readLine();
            System.out.println(messageDisconnected);
            ClientLoginData disconnected = ClientLoginData.deserialize(messageDisconnected);
            if(disconnected.getOperation().equals("disconnected"))
                socket.close();
            else{
                System.out.println("Client " + disconnected.getUsername()+ " not disconnected yet");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }


    private void login() {
        try {
            boolean loggato = false;

            while (loggato == false) {
                outVideo.println("Inserire login:");
                String userPlayer = inKeyboard.readLine();

                System.out.println(userPlayer);

                ClientLoginData msg = new ClientLoginData("login", userPlayer);
                msg.execute();
                String msg_str = msg.serializeLoginMessage();
                System.out.println(msg_str);
                this.outSocket.println(msg_str);
                /*outSocket.println("login");
                outSocket.flush();
                outSocket.println(username);
                outSocket.flush();*/

                //QUA TORNA UN TIPO MESSAGGIO!
                loggato = checklogin ();
                //loggato = Boolean.valueOf(inSocket.readLine()).booleanValue();

                if (loggato == true) {
                    outVideo.println("Login effettuato correttamente");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }

    public boolean checklogin() {

        try {
            String serveranswer = this.inSocket.readLine();
            System.out.println(serveranswer);
            ClientLoginData messageData = ClientLoginData.deserialize(serveranswer);
            System.out.println("username: " + messageData.getUsername());
            if (messageData.getOperation().equals("not_logged")){
                System.out.println("Name already present");
                return false;
            }
            else if(messageData.getOperation().equals(("too_many_users"))){

                System.out.println("Already 4 players!");
                return false;

            }

            else {
                System.out.println("Client recognized");
                this.username = messageData.getUsername();
                //prendi tutti i campi necessari al client
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void waitTime(){
        while(true);
    }

    //TODO remove
    public static void main(String[] args) throws IOException, InterruptedException
    {
        ClientImplementationSocket c=new ClientImplementationSocket();
        String s = "Chiara";
    }

}