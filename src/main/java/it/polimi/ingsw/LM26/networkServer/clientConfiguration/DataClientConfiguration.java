package it.polimi.ingsw.LM26.networkServer.clientConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

public class DataClientConfiguration {

    private final int ClientSOCKETPORT = 3095;
    private final int ServerRMIPORT=7095;
    private final int ClientRMIPORT = 1095;
    private final String ip = "127.0.0.1";


    public int getClientRMIPORT() {
        return ClientRMIPORT;
    }

    public int getServerRMIPORT(){ return ServerRMIPORT;}

    public int getClientSOCKETPORT() {
        return ClientSOCKETPORT;
    }

    public String getIp(){ return ip;}

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
                e.printStackTrace();
            }
        }
    }

}
