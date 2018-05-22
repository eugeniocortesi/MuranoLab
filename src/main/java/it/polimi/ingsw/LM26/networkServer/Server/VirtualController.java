package it.polimi.ingsw.LM26.networkServer.Server;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;

import java.rmi.RemoteException;

public class VirtualController implements VirtualControllerInt{
    private Controller controller;

    public boolean check(Die dieFromDraft, Box toBox, int player) throws RemoteException {
        return controller.check(dieFromDraft,toBox,player);
    }

    public boolean check(ToolCard twoThree, Box fromBox, Box toBox, int player) throws RemoteException {
        return controller.check(twoThree, fromBox, toBox, player);
    }

    public boolean check(ToolCard four, Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player) throws RemoteException {
        return controller.check(four, fromBox1, toBox1, fromBox2, toBox2, player);
    }

    public boolean check(ToolCard sixEightNine, Die dieFromDraft, Box toBox, int player) throws RemoteException {
        return controller.check(sixEightNine, dieFromDraft, toBox, player);
    }

    public boolean check(ToolCard five, Die dieFromDraft, Die dieFromRoundTrack, int player) throws RemoteException {
        return controller.check(five, dieFromDraft, dieFromRoundTrack, player);
    }

    public boolean check(ToolCard one, Die dieFromDraft, String inDecrement, int player) throws RemoteException {
        return controller.check(one, dieFromDraft, inDecrement, player);
    }

    public boolean check(ToolCard tenEleven, Die dieFromDraft, int player) throws RemoteException {
        return controller.check(tenEleven, dieFromDraft, player);
    }

    public boolean check(ToolCard seven, int player) throws RemoteException {
        return controller.check(seven, player);
    }

    public boolean check(String noAction, int player) throws RemoteException {
        return controller.check(noAction, player);
    }
}
