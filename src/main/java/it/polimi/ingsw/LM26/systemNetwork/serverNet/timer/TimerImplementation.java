package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.LM26.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TimerImplementation {

    private TimerConfiguration timerConfiguration;

    public TimerImplementation(){
        this.timerConfiguration =  new TimerConfiguration();
        //this.timerConfiguration.create();
    }

    public TimerConfiguration implentation(){

        try {
            InputStream stream = Main.class.getResourceAsStream("TimerConfiguration");
            //FileReader fr = new FileReader("src/main/resources/DataClientConfiguration");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            TimerConfiguration timer = gson.fromJson(br, TimerConfiguration.class);
            return timer;

            //InputStream reader = Main.class.getResourceAsStream("DataServerConfiguration");
        } catch (Exception e) {

            System.out.println("Timer not configured");
            return null;
        }
    }
}
