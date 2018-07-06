package it.polimi.ingsw.LM26.fileConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.LM26.Main;

import java.io.*;

/**
 * TimerConfiguration class
 * @author Chiara Criscuolo
 * It has the number of milliseconds when a timer finish
 */

public class TimerConfiguration {

    private long timerEnd = 180000;

    private long timerGameEnd = 10000;

    public long getTimerEnd() {
        return timerEnd;
    }

    public long getTimerGameEnd(){
        return timerGameEnd;
    }

    /**
     * Method tha creates the file TimerConfiguration
     */

    public void create(){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        FileWriter wr;

        {
            try {
                wr = new FileWriter("src/main/resources/TimerConfiguration");
                gson.toJson(this, wr);
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * method that reads the file TimerConfiguration and save timerEnd
     * @return TimerConfiguration class that memorize timerEnd
     * @exception Exception if it can't read the file amd return null
     */

    public TimerConfiguration implementation(){

        try {

            InputStream stream = Main.class.getResourceAsStream("TimerConfiguration");

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            return gson.fromJson(br, TimerConfiguration.class);

        } catch (Exception e) {

            System.err.println("Timer not configured");
            return null;
        }
    }
}
