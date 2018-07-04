package it.polimi.ingsw.LM26.view.cli;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class MyBuffer {
    private BufferedReader br;
    private boolean isReading;
    private String input;
    private static final Logger LOGGER = Logger.getLogger(MyBuffer.class.getName());

    MyBuffer(){
        br =  new BufferedReader(new InputStreamReader(System.in));
        isReading = false;
    }

    public String read() throws AlreadyReadingException{
        try{
            if(!isReading) {
                isReading = true;
                input = br.readLine();
                isReading = false;
                return input;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        throw new AlreadyReadingException();
    }

    public boolean isReading(){
        return isReading;
    }


    /*nel loop controlli se la stringa è uguale a "" e isReading(), allora non notifico niente
    e showMenu() termina (puoi chiamare direttamente return, credo)*/
}
