package it.polimi.ingsw.LM26.model;

public class SingletonModel {

    private static Model model;


    public static Model singletonModel(){

        return model;
    }

}
