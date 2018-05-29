package it.polimi.ingsw.LM26.systemNetwork.serverConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class DataServerConfiguration {

    private int ServerSOCKETPORT=3095;

    private int ServerRMIPORT=7095;

    private int ClientRMIPORT = 1095;

    private String ip = "127.0.0.1";

    public DataServerConfiguration(){

        ;
    }

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
                e.printStackTrace();
            }
        }
    }


}
