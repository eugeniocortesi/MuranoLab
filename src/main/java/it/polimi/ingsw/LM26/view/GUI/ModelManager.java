package it.polimi.ingsw.LM26.view.GUI;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Model;

public class ModelManager {

    static Model model;
    static int id;
    static ObjectivePrivateCard privateCard;

    public static Model getModel() {
        return model;
    }

    public static void setModel(Model model) {
        ModelManager.model = model;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ModelManager.id = id;
    }

    public static ObjectivePrivateCard getPrivateCard() {
        return privateCard;
    }

    public static void setPrivateCard(ObjectivePrivateCard privateCard) {
        ModelManager.privateCard = privateCard;
    }
}
