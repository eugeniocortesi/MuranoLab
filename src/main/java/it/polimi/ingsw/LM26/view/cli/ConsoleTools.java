package it.polimi.ingsw.LM26.view.cli;



import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.PatternBox;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackTurn;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.fusesource.jansi.AnsiConsole;

;import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleTools {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input;


    /*public String faces(int val){
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
    }*/

    /**
     * @return the size of the longest list of dice in the round track
     */
    public int maxSize(){
        int maxsize=0;
        for(int i=1; i<=ConsoleStrings.model.getRoundTrackInt().getRoundTrackTurnList().size(); i++){
            if(maxsize<ConsoleStrings.model.getRoundTrackInt().getRoundTrackTurn(i).size()){
                maxsize=ConsoleStrings.model.getRoundTrackInt().getRoundTrackTurn(i).size();
            }
        }
        return maxsize;
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

    /**
     * it shows the frame board updated at the end of the current player's turn
     */
    public void showYourplayerZone(int myid) {
        System.out.println("La tua area di gioco: ");
        this.printFrameBoard(ConsoleStrings.model.getPlayerList().get(myid));
        for(int i=0; i< ConsoleStrings.model.getPlayerList().get(myid).getToken().getTokenNumber();i++){
            System.out.print("\u25CB ");
            System.out.flush();
        }
        System.out.println();

        //show private card!!
    }

    public void showAnotherPlayer(int id){
        System.out.println("Area di gioco di "+ConsoleStrings.model.getPlayerList().get(id).getName());
        this.printFrameBoard(ConsoleStrings.model.getPlayerList().get(id));
        System.out.print("Segnalini favore: ");
        System.out.flush();
        for(int i=0; i< ConsoleStrings.model.getPlayerList().get(id).getToken().getTokenNumber();i++){
            System.out.print("\u25CB ");
            System.out.flush();
        }
        System.out.println();
    }

    /**
     * it shows the draft pool, the player zone
     */
    public void showPlacementDice(int myid){
        this.printDraftPool();
        this.showYourplayerZone(myid);
    }

    public void printPatternBox(PatternBox p){
        if(p.isColor()){
            String escape=p.getColor().escape();
            System.out.print(escape+"\u25A0"+"|"+Color.RESET);
            System.out.flush();
        }
        else if(p.isShade()){
            System.out.print((p.getValue())+"|");
            System.out.flush();
        }
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

   

    /**
     * it shows the updated round track table, with the first die of the stack for every turn
     */
    public void printRoundTrack(){
        DieInt die=null;
        int rtSize = ConsoleStrings.model.getRoundTrackInt().getRoundTrackTurnList().size();
        System.out.println("TRACCIATO DEI ROUND");
        System.out.println("1   2   3   4   5   6   7   8   9   10");
        for(int i=1; i<=rtSize; i++){
            for(int j=0; j<(maxSize()); j++){
                if(j<ConsoleStrings.model.getRoundTrackInt().getRoundTrackTurn(i).size()){
                     die = ConsoleStrings.model.getRoundTrackInt().getRoundTrackTurn(i).get(j);
                    System.out.print(die+"  ");
                }
                else System.out.print("   ");
            }
            System.out.println();
        }
    }

    public void printDraftPool(){
        System.out.println( ansi().eraseScreen().bold().a("riserva").boldOff());
        for(DieInt d : ConsoleStrings.model.getDraftPool().getInDraft()){
            System.out.print(d+"\t");
            System.out.flush();
        }
        System.out.println();
    }

    public String diceToString(DieInt die) throws IllegalArgumentException{
        String c;
        switch (die.getColor()){
            case ANSI_BLUE: c="B"; break;
            case ANSI_RED: c="R"; break;
            case ANSI_GREEN: c="VE"; break;
            case ANSI_PURPLE: c="VI"; break;
            case ANSI_YELLOW: c="G"; break;
            default: {
                throw new IllegalArgumentException("not colored die");
            }
        }
        switch (die.getValue()){
            case 1: return(c+1);
            case 2: return(c+2);
            case 3: return(c+3);
            case 4: return(c+4);
            case 5: return(c+5);
            case 6: return(c+6);
            default: {
                throw new IllegalArgumentException("die without face");
            }
        }
    }

    public void showCards(){
        boolean flag=false;
        System.out.println("'PR' per vedere la tua carta privata\n" +
                "'PU' per vedere quelle pubbliche\n" +
                "'U' per vedere le carte utensile");
        while(!flag){
            try{
                input = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            if(input.equalsIgnoreCase("PR")){

            }
        }
    }

   /* public int searchDraftPoolDie(char[] chars){
        int[]={};
        char c;
            switch (chars[0]){
                case 'B': for(DieInt i : ConsoleStrings.model.getDraftPool().getInDraft()){
                    if(i.getColor().equals(Color.ANSI_BLUE)){dcolored.add(i); c=chars[1];}
                }
                case 'R': for(DieInt i : ConsoleStrings.model.getDraftPool().getInDraft()){
                    if(i.getColor().equals(Color.ANSI_RED)){dcolored.add(i); c=chars[1];}
                }
                case 'G': for(DieInt i : ConsoleStrings.model.getDraftPool().getInDraft()){
                    if(i.getColor().equals(Color.ANSI_YELLOW)){dcolored.add(i); c=chars[1];}
                }
                case 'V': if(chars[1]=='E'){
                    for(DieInt i : ConsoleStrings.model.getDraftPool().getInDraft()){
                        if(i.getColor().equals(Color.ANSI_GREEN)){dcolored.add(i); c=chars[2];}
                    }
                }
                else if(chars[1]=='I'){
                    for(DieInt i : ConsoleStrings.model.getDraftPool().getInDraft()){
                        if(i.getColor().equals(Color.ANSI_PURPLE)){dcolored.add(i); c=chars[2];}
                    }
                }
            }


    }*/

    public void showInstructionsForPlacement(){
        System.out.println("Scegli un dado scrivendolo come colore+valore (es R2).\n" +
                "Colori: rosso R, blu B, giallo G, verde VE, viola VI\n" +
                "Valori da 1 a 6");
    }

    /*public void askForDie(){
        int r, c;
        PlayerZone playing=null;
        for(PlayerZone i : model.getPlayerList()){
            if(i.getPlayerState()== PlayerState.BEGINNING) playing=i;
        }
        boolean dieOk=false;
        char[] string={};
        ArrayList<String> diceTranslations = new ArrayList<String>();
        AnsiConsole.out().println("Scegli un dado tra quelli della riserva e inseriscilo nella tua plancia vetrata");
        for(int i=0; i<model.getDraftPool().getInDraft().size(); i++){
            diceTranslations.add(consoleTools.diceTranslate(model.getDraftPool().getInDraft().get(i)));
        }

        while(!dieOk){
            //consoleTools.showInstructionsForPlacement();
            System.out.println("Scegli dado numero: ");
            try{
                s = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            for(int i=0; i<diceTranslations.size(); i++){
                if(s.equalsIgnoreCase(diceTranslations.get(i))) {
                    dieOk=true;
                    s.getChars(0,s.length()-1, string, 0);
                }
            }
        }*/
        /*event = new ActionEvent();
        int id=0;
        BufferedReader read;


        System.out.println("insert id 1 o 9");
        read = new BufferedReader(new InputStreamReader(System.in));
        try {
            id = Integer.parseInt(read.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(id!=9) {

            //prendi indice dado
            //prendi indici i j

            int line = 0;
            System.out.println("insert line");
            read = new BufferedReader(new InputStreamReader(System.in));
            try {
                line = Integer.parseInt(read.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            int col = 0;
            System.out.println("insert col");
            read = new BufferedReader(new InputStreamReader(System.in));
            try {
                col = Integer.parseInt(read.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            int die = 0;
            System.out.println("insert die");
            read = new BufferedReader(new InputStreamReader(System.in));
            try {
                die = Integer.parseInt(read.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //prendi gli oggetti
            Box[][] board = playing.getPlayerBoard().getBoardMatrix();
            event.setId(id);
            event.setDieFromDraft((Die) model.getDraftPool().getInDraft().get(die - 1));
            event.setToBox1(board[line - 1][col - 1]);
            event.setPlayer(playing.getIDPlayer());
        }
        event.setId(id);
    }*/
}
