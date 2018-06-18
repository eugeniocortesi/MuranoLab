package it.polimi.ingsw.LM26.model;

public class SingletonModel {

    private static Model model;


    public static Model singletonModel(){


        if(model==null) model = new Model("ready");

        return model;
    }

}
