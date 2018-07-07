package it.polimi.ingsw.LM26.model;

/**
 * Model uses Singleton pattern
 * @author Eugenio Cortesi
 * class creates the Model Object if it does exist, otherwives if it does singleton method returns it
 */

public class SingletonModel {

    private static Model model;

    public static Model singletonModel(){

        if(model==null) {

            model = new Model();

            model.reset();
        }

        return model;
    }
}
