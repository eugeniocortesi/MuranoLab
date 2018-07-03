package it.polimi.ingsw.LM26.systemNetwork.netConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.LM26.Main;

import java.io.*;

/**
 * class DataClientConfiguration
 * @author Chiara Criscuolo
 * It memorize the 3 ports for Client Rmi and Socket Connection
 * and the ip address
 */

public class DataClientConfiguration {

    private int ClientSOCKETPORT = 3095;
    private int ServerRMIPORT=7095;
    private int ClientRMIPORT = 1095;
    private String ip = "127.0.0.1";


    public int getClientRMIPORT() {
        return ClientRMIPORT;
    }

    public int getServerRMIPORT(){ return ServerRMIPORT;}

    public int getClientSOCKETPORT() {
        return ClientSOCKETPORT;
    }

    public String getIp(){ return ip;}

    /**
     * Method that creates the file of "DataClientConfiguration"
     * with Json and FileWriter
     *
     */
    public void create(){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        FileWriter wr;

        {
            try {
                wr = new FileWriter("src/main/resources/DataClientConfiguration");
                gson.toJson(this, wr);
                wr.close();
            } catch (IOException e) {
                System.err.println("Writing DataClientConfiguration error");
            }
        }
    }

    public DataClientConfiguration implementation() {

        try {
            InputStream stream = Main.class.getResourceAsStream("DataClientConfiguration");

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            return gson.fromJson(br, DataClientConfiguration.class);

        } catch (Exception e) {

            System.err.println("Client not configured");
            return null;
        }
    }

}
