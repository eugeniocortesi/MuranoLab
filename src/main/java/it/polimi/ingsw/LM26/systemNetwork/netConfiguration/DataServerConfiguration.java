package it.polimi.ingsw.LM26.systemNetwork.netConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.LM26.Main;

import java.io.*;

/**
 * class DataServerConfiguration
 * @author Chiara Criscuolo
 * It memorize the 3 ports for Server and Client Rmi and Socket Connection
 * and the ip address
 */

public class DataServerConfiguration {

    private int ServerSOCKETPORT=3095;

    private int ServerRMIPORT=7095;

    private int ClientRMIPORT = 1095;

    private String ip = "127.0.0.1";

    public int getSOCKETPORT() {
        return ServerSOCKETPORT;
    }

    public int getServerRMIPORT() {
        return ServerRMIPORT;
    }

    public int getClientRMIPORT() {
        return ClientRMIPORT;
    }

    public String getIp(){ return ip;}

    /**
     * Method that creates the file DataServerConfiguration in Json with FileWriter
     */

    public void create(){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        FileWriter wr;

        {
            try {
                wr = new FileWriter("src/main/resources/DataServerConfiguration");
                gson.toJson(this, wr);
                wr.close();
            } catch (IOException e) {
                System.err.println("DataServerConfiguration not written");
            }
        }
    }

    public DataServerConfiguration implementation() {

        try {
            InputStream stream = Main.class.getResourceAsStream("DataServerConfiguration");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            return gson.fromJson(br, DataServerConfiguration.class);


        } catch (Exception e) {

            System.err.println("Server not configured");
            return null;
        }
    }

}
