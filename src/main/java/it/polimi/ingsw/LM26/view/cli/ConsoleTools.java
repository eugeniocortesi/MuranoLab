package it.polimi.ingsw.LM26.view.cli;



import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.PatternBox;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

;

public class ConsoleTools {



    public String faces(int val){
        switch (val){
            case 1:
                return "\u2680";
            case 2:
                return "\u2681";
            case 3:
                return "\u2682";
            case 4:
                return "\u2683";
            case 5:
                return "\u2684";
            case 6:
                return "\u2685";
            default:
                return null;
        }
    }

    /**
     * it only shows the frame board withits dice and its  window pattern card
     */
    public void printFrameBoard(PlayerZone pl){
        WindowFramePlayerBoard frame = pl.getPlayerBoard();
        //AnsiConsole.systemInstall();
        String escape= pl.getPlayerBoard().getColor().escape();
        System.out.println(escape+"\u2588\u2588 "+pl.getName().toUpperCase()+" \u2588\u2588"+ Color.RESET);
        for(int i=0; i<4; i++){
            for(int j=0; j<5; j++){
                Box box=frame.getBoardMatrix()[i][j];
                if(box.isIsPresent()){
                    System.out.print(box.getDie()+"|");
                    System.out.flush();
                }
                else printPatternBox(box.getPatternBox());
            }
            System.out.println();
        }
        //AnsiConsole.systemUninstall();
    }

    public void printPatternBox(PatternBox p){
        if(p.isColor()){
            String escape=p.getColor().escape();
            System.out.print(escape+"\u25A0"+"|");
            System.out.flush();
        }
        else if(p.isShade()){
            System.out.print(faces(p.getValue())+"|");
            System.out.flush();
        }
        else System.out.print(" |");
    }

    public void printPatternCard(String nameCard) throws IllegalArgumentException{
        WindowPatternCard windowPatternCard=null;
        for (WindowPatternCard i : ConsoleStrings.model.getDecks().getWindowPatternCardDeck()) {
            if (i.getTitle().equals(nameCard)) windowPatternCard = i;
        }
        if(windowPatternCard==null) throw new IllegalArgumentException("Wrong name of window pattern card");
        System.out.println(nameCard.toUpperCase()+": "+windowPatternCard.getToken()+" tokens");
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++) {
                printPatternBox(windowPatternCard.getWindowPatter()[i][j]);
            }
            System.out.println();
        }
    }


    /**
     * it shows the objective public card with identifier id from cards on board
     * @param id, is the card identifier
     */
    public void printObjectiveCardOnBoard(int id){
        ObjectivePublicCard objectivePublicCard=null;
        for(ObjectivePublicCard i : ConsoleStrings.model.getOnBoardCards().getObjectivePublicCardList()){
            if(i.getId()==id) objectivePublicCard=i;
        }
        if(objectivePublicCard==null) throw new IllegalArgumentException("Wrong identifier of public card on board");
        System.out.println("CARTA OBIETTIVO PUBBLICO");
        System.out.print("obiettivo");
        System.out.flush();
        switch(id){
            case 21: {System.out.println(": riga completa con colori tutti diversi"); break;}
            case 22: {System.out.println(": colonna completa con colori tutti diversi"); break;}
            case 23: {System.out.println(": riga completa con sfumature (cioè numeri) tutte diverse"); break;}
            case 24: {System.out.println(": colonna completa con sfumature (cioè numeri) tutte diverse"); break;}
            case 25: {System.out.println(" sfumature chiare: set di dadi (dello stesso colore) di valore 1 e 2 ovunque"); break;}
            case 26: {System.out.println(" sfumature medie: set di dadi (dello stesso colore) di valore 3 e 4 ovunque"); break;}
            case 27: {System.out.println(" sfumature scure: set di dadi (dello stesso colore) di valore 5 e 6 ovunque"); break;}
            case 28: {System.out.println(" set di dadi di tutti i colori ovunque"); break;}
            case 29: {System.out.println(" set di dadi di tutte le sfumature ovunque"); break;}
            case 210: {System.out.println(" dadi dello stesso colore diagonalmente adiacenti"); break;}
        }
        if (id!=10) System.out.println("punti per ogni volta che raggiungi l'obiettivo: "+objectivePublicCard.getPoints());
        else System.out.println("tanti punti quanti i dadi che formano una diagonale");
    }
}
