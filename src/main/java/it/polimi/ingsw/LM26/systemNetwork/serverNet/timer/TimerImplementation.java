package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.LM26.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * TimerImplementation class
 * Class that reads the file TimerConfiguration
 */
public class TimerImplementation {

    private TimerConfiguration timerConfiguration;

    public TimerImplementation(){
        this.timerConfiguration =  new TimerConfiguration();
        this.timerConfiguration.create();
    }

    /**
     * method that reads the file TimerCOnfiguration and save timerEnd
     * @return TimerConfiguration class that memorize timerEnd
     * @exception Exception if it can't read the file amd return null
     */
    public TimerConfiguration implentation(){

        try {
            InputStream stream = Main.class.getResourceAsStream("TimerConfiguration");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            TimerConfiguration timer = gson.fromJson(br, TimerConfiguration.class);
            return timer;

        } catch (Exception e) {

            System.out.println("Timer not configured");
            return null;
        }
    }
}
